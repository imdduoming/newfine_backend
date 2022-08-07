package com.example.nf.newfine_backend.attendance.domain;


import com.example.nf.newfine_backend.member.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class StudentAttendance {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "sattendance_id")
    private Long sAttendanceId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST})
    @JsonManagedReference
    private Attendance attendance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE})
    @JsonManagedReference
    private Student student;

    @Column
    private boolean attend = false;
    @Column
    private boolean islate = false;

    @Column
    private boolean isvideo = false;

    @Column
    private boolean receiveVideo = false;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime time;

    @Builder
    public StudentAttendance(Student student,Attendance attendance,LocalDateTime time,Boolean attend,Boolean islate,Boolean isvideo,Boolean receiveVideo) {
        this.student=student;
        this.attendance=attendance;
        this.time=time;
        this.attend=attend; // 출석  여부
        this.islate=islate; // 지각 여부
        this.isvideo=isvideo; // 동영상 신청 여부
        this.receiveVideo=receiveVideo; // 동영상 받았는지


    }




}
