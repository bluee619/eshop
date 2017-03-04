package com.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/main").permitAll()
			.antMatchers("/additem").authenticated()
			.antMatchers("/buyitem/**").authenticated()
			.antMatchers("/buycart").authenticated()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/myitems/**").authenticated()
		.and()
			.formLogin().loginPage("/login")
			.defaultSuccessUrl("/main")
			.failureUrl("/loginerror")
			.permitAll()
		.and()
			.logout()
			.invalidateHttpSession(true)
			.logoutUrl("/logout")
			.logoutSuccessUrl("/main")
		.and()
			.httpBasic()	
		.and()
			.csrf().disable();
		
		http
			.sessionManagement()
			.maximumSessions(1)
			.expiredUrl("/expired");
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
