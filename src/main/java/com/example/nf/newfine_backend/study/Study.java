package com.example.nf.newfine_backend.study;

import com.example.nf.newfine_backend.BaseTimeEntity;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Study {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "study_id")
    private Long studyId;

    @Column
    private String url;


    @OneToMany(mappedBy="study", cascade = { CascadeType.REMOVE})
    @JsonBackReference //순환참조 방지
    private List<StudentStudy> studentStudies;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime startTime;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime endTime;



    public Study (LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime=startTime;
        this.endTime=endTime;

    }
}
