package com.example.nf.newfine_backend.test.domain;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.member.domain.Timestamped;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Test {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "test_id")
    private Long id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate testDate;

    @Column
    private String testName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST})
    @JsonManagedReference
    private Course course;

    @OneToMany(mappedBy="test", cascade = { CascadeType.PERSIST})
    @JsonBackReference //순환참조 방지
    private List<CourseTestResults> courseTestResults;

    @OneToMany(mappedBy="test", cascade = { CascadeType.PERSIST})
    @JsonBackReference //순환참조 방지
    private List<StudentTestResults> studentTestResults;

    public Test(Course course, LocalDate testDate, String testName) {
        this.course=course;
        this.testDate=testDate;
        this.testName=testName;
    }
}
