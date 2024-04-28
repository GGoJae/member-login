package com.example.java8.vo;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MemberUpdateDTO {

    @NotNull
    private Boolean passwordChangeRequested;

    private String password;

    private String newPassword;

    private String confirmNewPassword;

    @NotBlank @Size(min = 4, max = 30)
    private String nickName;

    @NotBlank @Size(min = 2, max = 50)
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String phone;

    @Email
    private String email;
}
