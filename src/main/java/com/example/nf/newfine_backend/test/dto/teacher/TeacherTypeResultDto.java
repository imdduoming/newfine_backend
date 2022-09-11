package com.example.nf.newfine_backend.test.dto.teacher;

import com.example.nf.newfine_backend.test.dto.KillerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class TeacherTypeResultDto {
    private List<TeacherKillerDto> bkillerDtos;
    private List<TeacherKillerDto> killerDtos;
}