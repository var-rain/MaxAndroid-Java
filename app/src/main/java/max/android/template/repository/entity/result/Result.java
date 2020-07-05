package max.android.template.repository.entity.result;

/**
 * Created by: var_rain.
 * Created date: 2020/1/3.
 * Description: 返回数据格式
 */
@SuppressWarnings("unused")
public class Result<T> {

    // 状态码
    private int code;
    // 操作信息
    private String msg;
    // 数据
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
