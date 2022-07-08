package com.example.university.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private boolean success;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static Result ok(String message,Object data) {
        return new Result(true,message , data);
    }

    public static Result error(String msg) {
        return new Result(false, msg, null);
    }


}
