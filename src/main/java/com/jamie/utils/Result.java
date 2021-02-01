package com.jamie.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result ok() {
        return new Result().code(HttpStatus.OK.value());
    }

    public static Result error() {
        return new Result().code(HttpStatus.BAD_REQUEST.value());
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(Object data) {
        this.setData(data);
        return this;
    }
}