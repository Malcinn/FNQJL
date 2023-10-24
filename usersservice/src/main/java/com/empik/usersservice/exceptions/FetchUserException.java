package com.empik.usersservice.exceptions;

/**
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
public class FetchUserException extends Exception {

    public FetchUserException() {
    }

    public FetchUserException(String message) {
        super(message);
    }
}
