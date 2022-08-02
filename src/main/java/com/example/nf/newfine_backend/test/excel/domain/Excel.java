package com.example.nf.newfine_backend.test.excel.domain;

import com.example.nf.newfine_backend.member.domain.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Excel {

    @Id
    @Column(name = "excel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String a;

    @Column
    private String b;

    @Column
    private String c;

    @Column
    private String d;

    @Column
    private String e;

    @Column
    private String f;

    @Column
    private String g;

    @Builder
    public Excel(String a, String b, String c, String d, String e, String f, String g) {
        this.a=a;
        this.b=b;
        this.c = c;
        this.d = d;
        this.e= e;
        this.f=f;
        this.g=g;
    }
}
