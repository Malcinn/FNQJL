package com.empik.usersservice.data;

/**
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
public class ErrorWsDTO {
    private String message;

    private int errorCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
