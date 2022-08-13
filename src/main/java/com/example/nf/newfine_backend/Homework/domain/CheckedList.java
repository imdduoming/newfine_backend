package com.example.nf.newfine_backend.Homework.domain;

import lombok.Data;

import java.util.List;

@Data
public class CheckedList {
    private List<CheckedItem> items;
}
