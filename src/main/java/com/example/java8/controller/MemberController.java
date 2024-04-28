package com.example.java8.controller;

import com.example.java8.domain.Member;
import com.example.java8.service.MemberService;
import com.example.java8.vo.MemberSaveForm;
import com.example.java8.vo.MemberUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController("/api/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Long> memberEnroll(
            @ModelAttribute @Validated MemberSaveForm memberSaveForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Long memberId = memberService.enroll(memberSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberId);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Member>> memberList(Pageable pageable) {
        Page<Member> members = memberService.memberList(pageable);
        return ResponseEntity.ok()
                .body(members);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Long> memberUpdate(
            @PathVariable Long memberId,
            @RequestBody @Validated MemberUpdateDTO memberUpdateDTO, BindingResult bindingResult) {
        Long updateMemberId = memberService.memberUpdate(memberId, memberUpdateDTO);
        return ResponseEntity.ok()
                .body(updateMemberId);
    }

    @GetMapping("/loginId/check")
    public ResponseEntity<Boolean> isLoginIdAvailable(
            @RequestParam("loginId") String loginId) {
        Boolean availableLoginId = memberService.duplicateLoginIdConfirmed(loginId);
        return ResponseEntity.ok()
                .body(availableLoginId);
    }

    @GetMapping("/nickname/check")
    public ResponseEntity<Boolean> isNicknameAvailable(
            @RequestParam("nickname") String nickname) {
        Boolean availableNickname = memberService.duplicateNicknameConfirmed(nickname);
        return ResponseEntity.ok()
                .body(availableNickname);
    }



}
