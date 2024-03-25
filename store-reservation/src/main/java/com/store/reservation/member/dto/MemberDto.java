package com.store.reservation.member.dto;

import com.store.reservation.member.entity.MemberEntity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {


    private String userId;

    private String password;

    @Email
    private String email;

    //    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    @Pattern(regexp = "01(?:0|1|[6-9])[0-9]{7,8}")
    private String phoneNumber;


    public MemberEntity toEntity(MemberDto memberDto) {
        return MemberEntity.builder().
                userId(memberDto.getUserId()).
                password(memberDto.getPassword()).
                email(memberDto.getEmail()).
                phoneNumber(memberDto.getPhoneNumber()).
                build();
    }


}
