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
//public class SHomework extends Timestamped{
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    @Column(name = "SHomework_id")
//    private Long ShId;
//
//    @Column(nullable = false)
//    private String ShName;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private Student student;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private THomework tHomework;
//
//    // 파일 속성 추가해주세용
////    @Column(nullable = false)
////    private ;
//
//
//
//}
