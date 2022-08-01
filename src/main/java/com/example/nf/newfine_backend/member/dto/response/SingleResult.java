package com.example.nf.newfine_backend.member.dto.response;

import lombok.Data;

@Data
public class SingleResult<T> extends Result {
    private T data;
}

