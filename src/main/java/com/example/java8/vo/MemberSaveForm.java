package com.example.java8.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class MemberSaveForm {

    @NotBlank @Size(min = 4, max = 30)
    private String loginId;

    @NotBlank @Size(min = 4, max = 30)
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank @Size(min = 4, max = 30)
    private String nickName;

    @NotBlank @Size(min = 2, max = 50)
    private String name;

    // 01x-xxxx-xxxx 패턴 -을 포함한 핸드폰 번호
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String phone;

    @Email
    private String email;
}
