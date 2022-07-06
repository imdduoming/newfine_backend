//package com.example.nf.newfine_backend;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
//@Setter
//@Getter
//@NoArgsConstructor
//@Entity
//public class STest extends Timestamped{
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    @Column(name = "sTest_id")
//    private Long SteId;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private Student student;
//
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private TTest tTest;
//
//
//}
