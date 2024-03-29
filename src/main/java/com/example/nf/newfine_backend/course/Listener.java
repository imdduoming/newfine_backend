package com.example.nf.newfine_backend.course;

import com.example.nf.newfine_backend.member.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Listener {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "listener_id")
    private Long Id;

    @JsonManagedReference
    @ManyToOne
    private Course course;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;
    
//    @OneToMany(mappedBy="listener", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
//    @JsonBackReference //순환참조 방지
//    private List<SHomework> sHomeworks;

    @Builder
    public Listener(Course course,Student student) {
        this.course = course;
        this.student = student;
}


}
