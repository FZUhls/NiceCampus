package com.campus.nicecampus.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginValidateAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!userDetails.isEnabled()) {
            log.info("该账户已被禁用，请联系管理员");
            throw new DisabledException("该账户已被禁用，请联系管理员");

        } else if (!userDetails.isAccountNonLocked()) {
            log.info("该账号已被锁定");
            throw new LockedException("该账号已被锁定");

        } else if (!userDetails.isAccountNonExpired()) {
            log.info("该账号已过期，请联系管理员");
            throw new AccountExpiredException("该账号已过期，请联系管理员");

        } else if (!userDetails.isCredentialsNonExpired()) {
            log.info("该账户的登录凭证已过期，请重新登录");
            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录");
        }
        if (!rawPassword.equals(userDetails.getPassword())) {
            log.info("输入密码错误!");
            throw new BadCredentialsException("输入密码错误!");
        }
        log.info(username+": 登录成功");
        return new UsernamePasswordAuthenticationToken(userDetails, rawPassword, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
