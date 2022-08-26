package com.example.nf.newfine_backend.member.dto;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
//@Getter // push alarm 추가
//@Builder // push alarm 추가
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    private String phoneNumber;
    private String password;

    //push alarm 때문에 추가
    private String deviceToken;


//    @Builder
//    public SignInDto(String phonenumber, String password){
//        this.phonenumber=phonenumber;
//        this.password=password;
//    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(phoneNumber, password);
    }
}
