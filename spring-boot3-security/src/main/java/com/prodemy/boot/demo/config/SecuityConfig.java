/**
 * 
 */
package com.prodemy.boot.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Awiyanto Ajisasongko
 *
 * Feb 7, 2023
 */
@Configuration
@EnableWebSecurity
public class SecuityConfig  {
	@Autowired
	private DelegatedAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(c -> c.disable())
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/login",
							"/user/register",
							"/v3/api-docs/**",
							"/swagger-ui/**").permitAll();
				})
				.authorizeHttpRequests(auth -> {
					auth.anyRequest().authenticated();
				})
				.exceptionHandling(auth -> {
					auth.authenticationEntryPoint(authenticationEntryPoint);
				})
				.sessionManagement(auth -> {
					auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.build()
				;
	}
}
