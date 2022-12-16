package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.AbstractAuditable_;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeRequests()
		.antMatchers(HttpMethod.GET, //新的HttpSecurity 配置各種具體的驗證機制規則
				"/",
				"/401", 
				"/404", 
				"/500", 
				"/index", 
				"/login", 
				"/password", 
				"/postmodel/**", 
				"/register"
				).permitAll()
		.anyRequest().authenticated()
		
		.and()
        .csrf().disable()
        .formLogin();
		return httpSecurity.build();
	}
	
	@Bean WebSecurityCustomizer webSecurityCustomizer() { //新的WebSecurity 配置全局忽略的規則，如靜態資源、是否Debug等等
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**");
	}
	

}