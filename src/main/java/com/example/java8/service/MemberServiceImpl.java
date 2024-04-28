package com.example.java8.service;

import com.example.java8.domain.Member;
import com.example.java8.exception.InvalidPasswordException;
import com.example.java8.exception.MemberNoSuchException;
import com.example.java8.repository.MemberRepository;
import com.example.java8.vo.MemberSaveForm;
import com.example.java8.vo.MemberUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Long enroll(MemberSaveForm saveForm) {
        
        Member saveMember = Member.builder()
                .loginId(saveForm.getLoginId())
                .encryptedPassword(hashPassword(saveForm.getPassword()))
                .nickname(saveForm.getNickName())
                .name(saveForm.getName())
                .phone(saveForm.getPhone())
                .email(saveForm.getEmail())
                .enrolledTime(LocalDateTime.now())
                .build();

        return memberRepository.save(saveMember).getId();
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNoSuchException("Member with ID " + memberId + " does not exist"));
    }

    @Override
    public Page<Member> memberList(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public Long memberUpdate(Long memberId, MemberUpdateDTO updateForm) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNoSuchException("Member with ID " + memberId + " does not exist"));
        
        if (updateForm.getPasswordChangeRequested()) {
            updatePassword(member, updateForm);
        }

        if (StringUtils.hasText(updateForm.getName())) {
            member.setName(updateForm.getName());
        }
        if (StringUtils.hasText(updateForm.getNickName())) {
            member.setName(updateForm.getNickName());
        }
        if (StringUtils.hasText(updateForm.getPhone())) {
            member.setName(updateForm.getPhone());
        }
        if (StringUtils.hasText(updateForm.getEmail())) {
            member.setName(updateForm.getEmail());
        }

        return memberId;
    }

    @Override
    public Boolean duplicateLoginIdConfirmed(String loginId) {
        return !memberRepository.findByLoginId(loginId).isPresent();
    }

    @Override
    public Boolean duplicateNicknameConfirmed(String nickname) {
        return !memberRepository.findByNickname(nickname).isPresent();
    }

    private String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    private void updatePassword(Member target, MemberUpdateDTO updateForm) {

        if (!target.getEncryptedPassword().equals(hashPassword(updateForm.getPassword())) ||
                (!updateForm.getNewPassword().equals(updateForm.getConfirmNewPassword()))) {
            throw new InvalidPasswordException();
        }

        target.setEncryptedPassword(hashPassword(updateForm.getNewPassword()));
    }
}
