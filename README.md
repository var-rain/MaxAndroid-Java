#### MaxAndroid
[![Github release](https://img.shields.io/badge/MaxAndroid--Java-2.0.1-green.svg)]()

Android 应用程序快速开发聚合框架
#### 项目介绍
用于快速开发Android应用程序的模板项目,集成常用第三方依赖,各种封装,各种工具类,开箱即用,方便你快速开发出优质的APP
#### 功能介绍
- BaseActivity封装
- BaseFragment封装
- Android 4.4 + 沉静式控制封装
- Android 5.0 + 状态栏隐藏封装
- Android 5.0 + 导航栏隐藏封装
- Android 6.0 + 权限请求封装
- Android 6.0 + 状态栏图标明暗切换封装
- 全局异常捕获
- 异常信息收集
- 自定义异常信号处理( 登录验证拦截, 非空验证拦截, 代码运行截断等多种用途 )
- ViewPager Adapter
- 万能的 RecyclerView Adapter (来自 BaseRecyclerViewAdapterHelper )
- REST ful API 网络请求封装 ( OkHttp + Retrofit )
- 网络请求返回统一处理
- 网络请求线程调度封装 ( RxJava / RxAndroid )
- 网络请求日志打印
- 网络请求与 Activity/Fragment 生命周期绑定 ( RxLifecycle )
- 线程池
- scheme 外部启动拦截
- 闪屏 Activity ( 含跳过倒计时 )
- RecyclerView item 边距调整
- 状态栏占位
- 双波浪加载动画
- Dialog 对话框
- Loading 加载框
- Notice 应用内消息提示框
- Toast 消息提示
- 覆盖 View 封装
- InjectView View 插入 ( 用于礼物滚屏动画,各种对话框,界面内浮动按钮等 )
- BASE64 加解密工具类
- MD5 计算工具类
- JSON 序列化反序列化工具类 ( GSON )
- 像素单位转换工具类
- SharedPreferences 轻量数据储存封装
- 字符串工具类
- 手机号码正则
- APP 版本号获取
- Glide 图片加载集成
- CircleImageView 圆形图片集成
- Glide-Transformations 图像处理集成
- Realm 数据库集成
- Java Lambda 表达式支持
- Dex 拆分支持
- APK 打包特性配置
- APK 打包文件名带日期时间

#### 打开方式
- 点击此项目的 release 板块或[点击此处](https://github.com/scvax/MaxAndroid-Java/releases)前往下载
- 修改项目名
- 修改包名 ( Android Studio 快捷键 Shift + F6 )
- 修改 Subs 类中的请求状态码判断 ( 根据需求 )
- 修改 app/build.gradle 中的特性配置
- 修改 Androidmanifest.xml中的 scheme 协议名称
- 修改 SharedPreferencesUtils ( SharedPreferences ) 工具类中的储存文件名称
- 修改项目的 Git 目标地址
- 然后尽情享用吧

#### 更新日志
- #### 2.0.1
1. 移除事件总线依赖 ( Otto ) 原因是因为在部分手机上发现其存在某些问题,当然EventBus也同样,不推荐使用事件总线的方式,(滥用)这会是你的代码逻辑难以理解,并且会出现相当一部分问题
2. 移除Otto工具类
3. 移除 exceptions.crash 包下的 NoSignException 类,并删除 ExceptionHandle 类的 NoSignException 异常处理方法,需要则自行添加
4. 修改 BaseActivity & BaseFragment 权限请求的回调参数传递,详细修改细节请往下
5. 修改线程池的初始化创建方式,由原来的静态初始化修改为手动调用初始化,可选线程池类型,类名变更为 Worker 
6. 原 beans 包下的 SystemUIVisibility 类变更到 ui.base.config 包下
7. 修改默认消息提示方式由原来的 Notice 变更为 Toast
8. utils 包下的所有工具类统一添加 Utils 后缀
- #### 2.0.0
1. ```迁移Android支持库引用到AndroidX```
2. 升级OkHttp3 ( 3.14.0 -> 3.14.2 )
3. 升级Retrofit2 ( 2.4.0 -> 2.6.0 )
4. 升级Retrofit2:converter-gson ( 2.4.0 -> 2.6.0 )
5. 升级Retrofit2:adapter-rxjava2 ( 2.4.0 -> 2.6.0 )
6. 升级RxJava2 ( 2.2.8 -> 2.2.9 )
7. 升级RxLifecycle2 -> RxLifecycle3 ( 2.2.2 -> 3.0.0 )
8. 升级ButterKnife ( 9.0.0 -> 10.1.0 )
9. 升级ButterKnife-Compiler ( 9.0.0 -> 10.1.0 )
10. 升级Glide ( 4.8.0 -> 4.9.0 )
11. 升级Glide-Compiler ( 4.8.0 -> 4.9.0 )
12. 升级Glide-Transformations ( 3.0.1 -> 4.0.1 )
13. 移除android.support.v4包
14. 移除日志打印 ( com.orhanobut.logger )
15. 移除下拉刷新上拉加载支持 ( SmartRefreshLayout & SmartRefreshHeader )
16. 移除Banner


#### 代码演示
- Activity / Fragment
```java
/**
 * 继承自 BaseActivity / BaseFragment
 */
public class SimpleActivity extends BaseActivity {

    @BindView(R.id.simple_text_view)
    TextView mTextView;

    @Override
    protected int onLayout() {
        /* 指定 layout 资源 */
        return R.layout.activity_simple;
    }

    @Override
    protected void onObject() {
        /* 初始化对象 */
    }

    @Override
    protected void onView() {
        /* 初始化View */
    }

    @Override
    protected void onData() {
        /* 初始化数据 */
    }

    /**
     * 使用 ButterKnife 绑定事件
     */
    @OnClick(R.id.simple_text_view)
    public void onViewClicked() {
        
    }
}
```
- 系统UI控制( 状态栏, 导航栏等 )
```java
public class SimpleActivity extends BaseActivity {
    ......
    /**
     * 重写 onSystemUIVisibility 方法
     */
    @Override
    protected void onSystemUIVisibility(SystemUIVisibility config) {
        /* 设置状态栏图标明暗 */
        config.setDarkStatusBar(false);
        /* 是否显示状态栏 */
        config.setHideStatusBar(false);
        /* 是否隐藏导航栏 */
        config.setHideNavigationBar(true);
        /* 是否透明状态栏 */
        config.setTranslucentStatusBar(true);
        /* 是否透明导航栏 */
        config.setTranslucentNavigationBar(true);
    }
    ......
}
```
- Android 6.0 + 权限请求
```java
public class SimpleActivity extends BaseActivity {
    
    private String[] permissions;

    @Override
    protected void onObject() {
        /* 初始化权限列表 */
        permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        }
    }

    @Override
    protected void onData() {
        /* 在继承自 BaseActivity 或 BaseFragment 的任意方法中调用均可 */
        requestSelfPermission(permissions, new OnPermissionRequestListener() {

            /**
             * @param authorize     是否全部授权成功
             * @param permissions   未通过授权的权限,在 authorize 返回 true 的时候该数据列表为空 ( size = 0 )
             */
            @Override
            public void onPermissionRequest(boolean authorize, @NonNull List<String> permissions) {
                /* authorize 为 true 的情况下即所有权限授权通过,为 false 的情况下 permissions 中的权限名称均为未授权的权限 */
            }
        });
    }
}
```
- 网络请求
```java
public class SimpleActivity extends BaseActivity {

    ......

    @Override
    protected void onData() {
        User user = new User();
        user.setMobile("1234567890");
        user.setSMSCode("123456");
        Network.api()
                .login(user)
                .compose(Scheduler.apply(bindUntilEvent(ActivityEvent.DESTROY)))
                .subscribe(new Subs<Object>(){

                    @Override
                    protected void onCompleted() {
                        /* 请求成功与否都会调用,选择性重写 */
                    }

                    @Override
                    protected void onSuccess(Object data) {
                        /* 请求成功 */
                    }

                    @Override
                    protected void onFail() {
                        /* 请求失败,选择性重写 */
                    }
                });
    }

    ......

}
```
- 其他的基本都很简单了,就不一一举例了,毕竟代码注释还算看得过去,简单的看一下基本就会用了

#### 最后
如果大家有什么好的建议或意见可以说出来,让这个项目更加完善,我也会不断的补充和修改此项目,以达到更好的解决开发中的各种繁琐复杂的工作

#### 另外
MAX 计划开启