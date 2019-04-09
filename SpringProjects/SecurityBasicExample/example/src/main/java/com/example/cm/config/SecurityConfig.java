package com.example.cm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.cm.service.cmUserAccountService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private cmUserAccountService userAccountService;

    private final String login_page = "/login";

    //Bean으로 등록된 BCrypt를 받아온다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //초기화 과정에서 userAccountService에 인코더를 BCrypt로 설정.
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAccountService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userAccountService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/script/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http
                .cors().and()
                .authorizeRequests()
                .antMatchers("/html/login/**", "/login/**", "/css/**").permitAll()
                .antMatchers("/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage(login_page)
                .loginProcessingUrl("/logindata")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/")
                .failureUrl(login_page)
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl(login_page);
    }
}
