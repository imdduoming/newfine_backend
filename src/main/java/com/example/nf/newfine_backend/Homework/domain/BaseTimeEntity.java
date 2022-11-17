package com.example.nf.newfine_backend.Homework.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime modifiedDate;

//    @LastModifiedDate
//    private String checkedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
