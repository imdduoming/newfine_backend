package com.example.nf.newfine_backend.student.dto.response;

import lombok.Data;

@Data
public class SingleResult<T> extends Result {
    private T data;
}

