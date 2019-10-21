package com.tomimavrin.projectmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Configuration
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        DataSource dataSource;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT email,password,enabled FROM USERS WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, authority FROM AUTHORITIES WHERE email = ?");
        }



        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/hello", "/register")
                    .permitAll()
                    .anyRequest()
                    .authenticated();

            http.csrf()
                    .disable();
            http.httpBasic();
        }

    }
}
