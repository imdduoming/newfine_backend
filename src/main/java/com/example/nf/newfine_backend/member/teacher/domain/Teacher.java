package com.example.nf.newfine_backend.member.teacher.domain;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.member.domain.Authority;
import com.example.nf.newfine_backend.member.domain.Timestamped;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long tId;

    @Column
//    @NotNull
//    @Pattern(regexp = "^(010[1|6|7|8|9|0])(\\d{3,4})(\\d{4})$")
    private String phoneNumber;


    @Column(nullable = false)
//    @NotNull
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@^!%*#?&])[A-Za-z\\d@^!%*#?&]{8,}$")
    private String tPassword;

    @Column(nullable = false)
    @NotNull
    private String tName;

    @Enumerated(EnumType.STRING)
    private Authority tAuthority;

    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy="teacher")
    private List<Course> courses;


//    @JsonIgnore
//    @OneToMany(mappedBy="teacher", cascade = { CascadeType.PERSIST})
//    private List<THomework> tHomeworks;


    @Column
    private LocalDateTime signupDate;

    @Column(name = "device_token", nullable = true)
    private String deviceToken;

    @PrePersist
    public void signupDate() {
        this.signupDate = LocalDateTime.now();
    }

    @Builder
    public Teacher(String phoneNumber, String tName, String tPassword, Authority tAuthority, String deviceToken) {
        this.phoneNumber=phoneNumber;
        this.tName=tName;
        this.tPassword = tPassword;
        this.tAuthority = tAuthority;
        this.deviceToken = deviceToken;
    }

}
