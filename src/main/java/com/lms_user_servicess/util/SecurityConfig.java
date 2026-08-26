package com.lms_user_servicess.util;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .csrf().disable()

            .authorizeRequests()

            // Login APIs - no authentication required
            .antMatchers(
                "/api/v1/user/login",
                "/api/v1/user/register",
                "/api/v1/R.E/login"
            ).permitAll()

            // Swagger
            .antMatchers(
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/webjars/**"
            ).permitAll()

            // All remaining APIs require authentication
            .anyRequest().authenticated()

            .and()
            .httpBasic().disable()
            .formLogin().disable();
    }
}