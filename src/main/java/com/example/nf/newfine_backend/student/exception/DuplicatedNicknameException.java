package com.example.nf.newfine_backend.student.exception;

public class DuplicatedNicknameException extends RuntimeException {
    public DuplicatedNicknameException() {
        super();
    }

    public DuplicatedNicknameException(String message) {
        super(message);
    }
}
