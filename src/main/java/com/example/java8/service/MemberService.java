package com.example.java8.service;

import com.example.java8.domain.Member;
import com.example.java8.vo.MemberSaveForm;
import com.example.java8.vo.MemberUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberService{

    Long enroll(MemberSaveForm saveForm);

    Member findMemberById(Long memberId);

    Page<Member> memberList(Pageable pageable);

    Long memberUpdate(Long memberId, MemberUpdateDTO updateForm);

    Boolean duplicateLoginIdConfirmed(String loginId);

    Boolean duplicateNicknameConfirmed(String nickname);
}
