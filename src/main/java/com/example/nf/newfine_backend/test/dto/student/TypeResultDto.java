package com.example.nf.newfine_backend.test.dto.student;


import com.example.nf.newfine_backend.test.dto.KillerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TypeResultDto {
    private List<KillerDto> bkillerDtos;
    private List<KillerDto> killerDtos;
}
