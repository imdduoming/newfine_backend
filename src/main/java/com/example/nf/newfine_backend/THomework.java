//package com.example.nf.newfine_backend;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.mapping.ToOne;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
//@Setter
//@Getter
//@NoArgsConstructor
//@Entity
//public class THomework extends Timestamped{
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    @Column(name = "tHomework_id")
//    private Long ThId;
//
//    @Column(nullable = false)
//    private String ThName;
//
//    @OneToMany(mappedBy="course", cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
//    private List<SHomework> sHomeworks;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
//    private Course course;
//
////    @Column(nullable = false)
////    private Date startTime;
//
//    @Column(nullable = false)
//    private Date Deadline1;
//
//    @Column(nullable = false)
//    private Date Deadline2;
//
//    @Column(nullable = false)
//    private String text;
//
//
//
//}
