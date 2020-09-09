package com.cheerios.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.cheerios.security.enums.UserPermission;
import com.cheerios.security.enums.UserRoles;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
		.antMatchers("api/v1/**").hasRole(UserRoles.STUDENT.name())
		//.antMatchers(HttpMethod.DELETE, "management/api/v1/**").hasAuthority(UserPermission.COURSE_WRITE.name())
		//.antMatchers(HttpMethod.GET, "management/api/v1/**").hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ADMIN_TRAINEE.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails annaSmith = User.builder()
				.username("Anna-Nichole")
				.password(passwordEncoder.encode("password"))
				.authorities(UserPermission.STUDENT_READ.name())
				.roles(UserRoles.STUDENT.name()).build();
		
		UserDetails linda = User.builder()
				.username("linda")
				.password(passwordEncoder.encode("password123"))
				.authorities(UserPermission.COURSE_WRITE.name(), UserPermission.COURSE_READ.name())
				.roles(UserRoles.ADMIN.name()).build();
		
		return new InMemoryUserDetailsManager(annaSmith, linda);
	}

	
}
