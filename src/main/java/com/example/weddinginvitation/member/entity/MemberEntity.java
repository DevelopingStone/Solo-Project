package com.example.weddinginvitation.member.entity;

import com.example.weddinginvitation.member.config.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Email
    @Column(unique = true)
    private String email;

    @Nullable
    private String name;

    @Nullable
    @Column(unique = true)
    private String nick_name;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    @Column(unique = true)
    @NotBlank
    private String phone_number;

    @NotBlank
    private String text_authentication_number;

    @NotNull
    private boolean text_authentication;

}
