package com.example.nf.newfine_backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Study {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "course_id")
    private Long ssId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    private Student student;

    @Column(nullable = false)
    private Date entranceTime;

    @Column(nullable = false)
    private Date endTime;


}
