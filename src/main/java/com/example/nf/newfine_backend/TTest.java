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
//public class TTest {
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    @Column(name = "tTest_id")
//    private Long TteId;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private Teacher teacher;
//
//
//    @OneToMany(mappedBy="course", cascade = { CascadeType.REMOVE})
//    private List<STest> sTests;
//
//    @Column(nullable = false)
//    private Date entranceTime;
//
//    @Column(nullable = false)
//    private Date endTime;
//
//
//}
