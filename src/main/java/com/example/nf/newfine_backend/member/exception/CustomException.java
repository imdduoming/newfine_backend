package com.example.nf.newfine_backend.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException { // Unchecked Exception
    private final ErrorCode errorCode;
}
