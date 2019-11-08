package com.abc.httplibs.bean;

public class BaseResultData<T> {
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 状态描述
     */
    private String message;

    /**
     * 结果数据
     */
    private T result;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseResultData{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}

