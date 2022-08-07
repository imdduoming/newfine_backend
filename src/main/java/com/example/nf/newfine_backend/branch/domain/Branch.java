package com.example.nf.newfine_backend.branch.domain;

import com.example.nf.newfine_backend.course.Course;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.test.domain.CourseTestResults;
import com.example.nf.newfine_backend.test.domain.Test;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Branch {

    @Id
    @Column(name = "branch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private BranchName branchName;

    @OneToMany(mappedBy="branch", cascade = { CascadeType.PERSIST})
    @JsonBackReference //순환참조 방지
    private List<BranchStudent> branchStudent;

//    @OneToMany(mappedBy="branch", cascade = { CascadeType.PERSIST})
//    @JsonBackReference //순환참조 방지
//    private List<Student> students;

    @JsonBackReference  // 순환참조 방지
    @OneToMany(mappedBy = "branch", cascade = {CascadeType.PERSIST})
    private List<Course> courses;
}
