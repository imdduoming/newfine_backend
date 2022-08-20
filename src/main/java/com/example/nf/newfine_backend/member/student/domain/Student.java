package com.example.nf.newfine_backend.member.student.domain;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Listener;
import com.example.nf.newfine_backend.member.domain.Authority;
import com.example.nf.newfine_backend.member.domain.Timestamped;
import com.example.nf.newfine_backend.study.StudentStudy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends Timestamped {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
//    @NotNull
//    @Pattern(regexp = "^(010[1|6|7|8|9|0])(\\d{3,4})(\\d{4})$")
    private String phoneNumber;

    @Column
//    @NotNull
    private String name;

    @JsonIgnore
    @Column
//    @NotNull
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@^!%*#?&])[A-Za-z\\d@^!%*#?&]{8,}$")
    private String password;

    @Column
    private String nickname;

    @Column
    private String photoURL;

    @Column
    @ColumnDefault("0")
    private Integer point;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column
    private String test_code;

    @OneToMany(mappedBy="student", cascade = { CascadeType.REMOVE})
    @JsonBackReference //순환참조 방지
    private List<StudentAttendance> studentAttendancces;

    @OneToMany(mappedBy="student", cascade = { CascadeType.REMOVE})
    @JsonBackReference //순환참조 방지
    private List<StudentStudy> studentStudies;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="student", cascade = { CascadeType.REMOVE})
    private List<Listener> listeners;

    @Column
    @Enumerated(EnumType.STRING)
    private Tier tier = Tier.NEW;

//    private LocalDateTime levelUpDate;


    @Column
    private LocalDateTime signupDate;

    @PrePersist
    public void signupDate() {
        this.signupDate = LocalDateTime.now();
    }

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="owner", orphanRemoval = true, cascade = CascadeType.REMOVE)  // 주체는 Point 객체
    private List<Point> pointList=new ArrayList<>();

//    @JsonManagedReference
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
//    private Branch branch;

    //디바이스 토큰 추가
    @Column(name = "device_token")
    private String deviceToken = "1";


    @Builder
    public Student(String phoneNumber, String name, String password, String nickname, Authority authority, String photoURL, Integer point, String deviceToken) {
        this.phoneNumber=phoneNumber;
        this.name=name;
        this.password = password;
        this.authority = authority;
        this.nickname= nickname;
        this.photoURL=photoURL;
        this.point=point;
        this.deviceToken= deviceToken;
//        this.branch=branch;
    }

    public void modifyDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

//    public boolean availableLevelUp() {
//        return Level.availableLevelUp(this.getLevel(), this.getPoint());
//    }
//
//    public Level levelUp() {
//        Level nextLevel = Level.getNextLevel(this.getPoint());
//        this.level = nextLevel;
//        this.levelUpDate = LocalDateTime.now();
//        System.out.println(level);
//        System.out.println(point);
//        return nextLevel;
//    }
}
