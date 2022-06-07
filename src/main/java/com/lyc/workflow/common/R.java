package com.lyc.workflow.common;


import lombok.Data;


/**
 * @param <T>
 * @author liyc
 */
@Data
public class R<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据类型
     */
    private T data;

    private R() {
    }

    public static <T> R<T> success(T data) {

        return buildResponse(data, 200, "success");
    }

    public static <T> R<T> success(T data, String msg) {
        return buildResponse(data, 200, msg);
    }

    public static <T> R<T> error(Integer code, String msg) {
        return buildResponse(null, code, msg);
    }

    public static <T> R<T> error(Integer code, String msg, T data) {
        return buildResponse(data, code, msg);
    }

    public static <T> R<T> error(String msg) {
        return buildResponse(null, 500, msg);
    }

    private static <T> R<T> buildResponse(T data, Integer code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
