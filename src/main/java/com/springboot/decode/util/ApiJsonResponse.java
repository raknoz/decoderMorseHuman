package com.springboot.decode.util;

import java.io.Serializable;

/**
 * @author David 5/11/2018
 */
public class ApiJsonResponse<T> implements Serializable {

    public static final Integer SUCCESS = 0;
    public static final Integer ERROR = 1;

    Integer code;
    String message;
    T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiJsonResponse success() {
        this.code = this.SUCCESS;
        this.message = "";
        this.data = null;
        return this;
    }

    public ApiJsonResponse success(String message) {
        this.success();
        this.message = message;
        return this;
    }
    public ApiJsonResponse success(T object, String message) {
        this.success();
        this.data = object;
        this.message = message;
        return this;
    }

    public ApiJsonResponse success(Integer code, T object) {
        this.code = code;
        this.data = object;
        return this;
    }

    public ApiJsonResponse success(T object) {
        this.success();
        this.data = object;
        return this;
    }

    public ApiJsonResponse error() {
        this.code = this.ERROR;
        this.message = "";
        this.data = null;
        return this;
    }

    public ApiJsonResponse error(String message) {
        this.error();
        this.message = message;
        return this;
    }

    public ApiJsonResponse error(Integer code, String message) {
        this.error();
        this.code = code;
        this.message = message;
        return this;
    }
}
