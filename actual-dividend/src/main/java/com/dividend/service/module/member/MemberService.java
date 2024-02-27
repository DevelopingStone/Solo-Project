package com.dividend.service.module.member;

import com.dividend.model.domain.Member;
import com.dividend.persist.entity.MemberEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    UserDetails loadUserByUsername(String username);

    MemberEntity register(Member.SignUp member);

    MemberEntity authenticate(Member.SignIn member);
}
