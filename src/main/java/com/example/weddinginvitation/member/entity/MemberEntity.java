package com.example.weddinginvitation.member.entity;

import com.example.weddinginvitation.member.config.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@ToString
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Email
    @Column(unique = true)
    private String email;

    private String name;

    @Nullable
    @Column(unique = true)
    private String nickName;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    @Column(unique = true)
    private String phoneNumber;

    private boolean textAuthentication;

    private String textAuthenticationNumber;

    public void updateTextAuthenticationNumber(String authenticationNumber) {
        this.textAuthenticationNumber = authenticationNumber;
    }

    public void updateTextAuthentication() {
        this.textAuthentication = true;
    }

}
