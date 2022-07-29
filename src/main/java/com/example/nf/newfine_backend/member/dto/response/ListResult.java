package com.example.nf.newfine_backend.member.dto.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ListResult<T> extends Result {
    private List<T> data;
    private boolean hasNext;
}
