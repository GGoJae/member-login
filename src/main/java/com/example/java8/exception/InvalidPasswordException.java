package com.example.java8.exception;

public class InvalidPasswordException extends MemberException{

    static final String DEFAULT_MESSAGE = "비밀번호를 확인해주세요";

    public InvalidPasswordException() {
        super(DEFAULT_MESSAGE);
    }
    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
