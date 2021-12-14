package com.campus.nicecampus.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginValidateAuthenticationProvider loginValidateAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/login","/loginning","/failure","/user/register").permitAll()
                .anyRequest().authenticated().and().formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("pwd")
                .loginProcessingUrl("/loginning")
                .failureUrl("/failure")
                .defaultSuccessUrl("/",true)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginValidateAuthenticationProvider);
    }
}
