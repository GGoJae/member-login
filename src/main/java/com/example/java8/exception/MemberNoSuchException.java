package com.example.java8.exception;

import java.util.NoSuchElementException;

public class MemberNoSuchException extends MemberException {

    static final String DEFAULT_MESSAGE = "회원을 찾을 수 없습니다.";

    public MemberNoSuchException() {
        super(DEFAULT_MESSAGE);
    }
    public MemberNoSuchException(String message) {
        super(message);
    }

    public MemberNoSuchException(String message, Throwable cause) {
        super(message, cause);
    }
}
