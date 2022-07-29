package com.example.nf.newfine_backend.member.student.exception;

public class PhoneNumberNotFoundException extends RuntimeException{
    public PhoneNumberNotFoundException() {
        super();
    }

    public PhoneNumberNotFoundException(String message) {
        super(message);
    }
}
