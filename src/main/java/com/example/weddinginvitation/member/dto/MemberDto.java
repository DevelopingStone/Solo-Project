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
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class MemberDto {

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Nullable
    private String name;

    @Nullable
    private String nickName;

    @NotBlank
    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phoneNumber;

    @NotNull
    private boolean textAuthentication;

    @NotBlank
    private String textAuthenticationNumber;


    public MemberEntity toEntity(HashMap<String, Object> userInfo) {

        return MemberEntity.builder().
                email((String) userInfo.get("email")).
                nickName((String) userInfo.get("nickname")).
                textAuthentication(false).
                role(Role.MEMBER).
                build();

    }

    public MemberEntity toEntity(MemberEntity existingMember, String authenticationNumber) {

        existingMember.setTextAuthenticationNumber(authenticationNumber);
        existingMember.setPhoneNumber(this.phoneNumber);
        existingMember.setName(this.name);
        return existingMember;

    }


    public MemberEntity toEntity(MemberEntity memberEntity) {

        memberEntity.setTextAuthentication(true);
        return memberEntity;

    }

}
