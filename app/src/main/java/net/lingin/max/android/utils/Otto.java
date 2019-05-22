package net.lingin.max.android.utils;

import com.squareup.otto.Bus;

/**
 * Created by: var_rain.
 * Created date: 2018/12/22.
 * Description: 事件总线工具类
 */
public class Otto {

    /* 事件总线 */
    private static Bus bus;

    /**
     * 注册事件总线
     *
     * @param object 消息分发的目标
     */
    public static void register(Object object) {
        if (Otto.bus == null) {
            Otto.bus = new Bus();
        }
        Otto.bus.register(object);
    }

    /**
     * 反注册事件总线
     *
     * @param object 消息分发的目标
     */
    public static void unregister(Object object) {
        if (Otto.bus == null) {
            return;
        }
        Otto.bus.unregister(object);
    }

    /**
     * 发送事件
     *
     * @param event 事件消息对象
     */
    public static void post(Object event) {
        if (Otto.bus == null) {
            return;
        }
        Otto.bus.post(event);
    }
}
