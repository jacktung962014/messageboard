package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityForBCryptEncoder extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 因為沒有要使用Spring Security，而是只拿裡面的BCryptPasswordEncoder用於加密
		// 所以什麼都不設，讓所有API又恢復到能任意存取的狀態。
		
		
//		http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
//		http.csrf().disable();
//		http
//		.csrf().disable().authorizeRequests()
//		.antMatchers("/password").hasRole("Admin")
//		.anyRequest().authenticated();
		
//      for application.properties
//		spring.security.user.name=podi
//		spring.security.user.password=podi
//		pring.security.user.roles=admin
		
	}
}
