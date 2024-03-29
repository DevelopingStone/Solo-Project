package com.example.weddinginvitation.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MemberEntity {

    @Id
    private Long member_id;

    @Email
    private String email;

    @Nullable
    private String name;

    @Nullable
    @Column(unique = true)
    private String nick_name;

    @Nullable
    private String phone_number;

    @Nullable
    private String text_number;

    private boolean text_authentication;


}
