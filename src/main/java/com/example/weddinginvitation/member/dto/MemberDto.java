package com.example.weddinginvitation.member.dto;

import com.example.weddinginvitation.member.config.Role;
import com.example.weddinginvitation.member.entity.MemberEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.HashMap;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {


    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Nullable
    private String name;

    @Nullable
    private String nick_name;

    @NotBlank
    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phone_number;

    @NotNull
    private boolean text_authentication;

    @NotBlank
    private String text_authentication_number;


    public MemberEntity toEntity(MemberDto memberDto, HashMap<String, Object> userInfo) {
        return MemberEntity.builder().
                email((String) userInfo.get("email")).
                role(memberDto.getRole()).
                name(memberDto.getName()).
                nick_name((String) userInfo.get("nickname")).
                phone_number(memberDto.getPhone_number()).
                text_authentication(memberDto.text_authentication).
                text_authentication_number(memberDto.getText_authentication_number()).
                build();
    }

}
