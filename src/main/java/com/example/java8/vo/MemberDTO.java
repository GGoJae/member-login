package com.example.java8.vo;

import com.example.java8.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class MemberDTO {

    private Long id;

    private String loginId;

    private String nickname;

    private String name;

    private String phone;

    private String email;

    private LocalDateTime enrolledTime;

    private MemberDTO() {
    }

    public static MemberDTO convertToDto (Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setLoginId(member.getLoginId());
        memberDTO.setName(member.getName());
        memberDTO.setNickname(member.getNickname());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setEnrolledTime(member.getEnrolledTime());

        return memberDTO;
    }
}
