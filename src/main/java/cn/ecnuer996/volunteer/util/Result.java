package cn.ecnuer996.volunteer.util;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 *
 * @author xusheng
 */
public class Result<T> {
    private int code;
    private String message;
    private T result;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getResult() {
        return result;
    }

    public Result setResult(T data) {
        this.result = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
