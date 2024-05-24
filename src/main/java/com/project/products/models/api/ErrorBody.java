package com.project.products.models.api;

public class ErrorBody {
    private String message;
    private String code;

    public ErrorBody(String message, String code){
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String messsage) {
        this.message = messsage;
    }
}
