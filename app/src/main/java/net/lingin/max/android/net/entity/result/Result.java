package net.lingin.max.android.net.entity.result;

/**
 * Created by: var_rain.
 * Created date: 2018/10/21.
 * Description: 统一返回对象
 */
public class Result<T> {

    /* 返回状态码 */
    private int code;
    /* 提示信息 */
    private String msg;
    /* Token */
    private String token;
    /* 数据对象 */
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
