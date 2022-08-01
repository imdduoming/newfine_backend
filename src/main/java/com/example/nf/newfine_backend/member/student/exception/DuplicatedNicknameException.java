package com.example.nf.newfine_backend.member.student.exception;

public class DuplicatedNicknameException extends RuntimeException {
    public DuplicatedNicknameException() {
        super();
    }

    public DuplicatedNicknameException(String message) {
        super(message);
    }
}
