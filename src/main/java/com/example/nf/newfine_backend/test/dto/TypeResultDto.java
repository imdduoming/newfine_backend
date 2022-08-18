package com.example.nf.newfine_backend.test.dto;


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
