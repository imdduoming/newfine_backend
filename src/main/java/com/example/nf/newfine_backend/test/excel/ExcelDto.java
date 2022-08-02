package com.example.nf.newfine_backend.test.excel;

import com.example.nf.newfine_backend.member.domain.Authority;
import com.example.nf.newfine_backend.member.student.domain.Student;
import com.example.nf.newfine_backend.test.excel.domain.Excel;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDto {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public Excel toExcel() {
        return Excel.builder()
                .a(a)
                .b(b)
                .c(c)
                .d(d)
                .e(e)
                .f(f)
                .g(g)
                .build();
    }
}
