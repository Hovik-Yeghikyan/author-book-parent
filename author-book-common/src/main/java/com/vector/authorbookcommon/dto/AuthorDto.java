package com.vector.authorbookcommon.dto;


import com.vector.authorbookcommon.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private Gender gender;
}
