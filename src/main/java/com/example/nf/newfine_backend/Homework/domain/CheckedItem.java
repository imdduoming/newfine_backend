package com.example.nf.newfine_backend.Homework.domain;

import lombok.Data;

@Data
public class CheckedItem {
    private Long id;
    private Long shId;
    private String name;
    private String title;
    private char grade;
    private boolean ischecked;
    private boolean disabled;
}
