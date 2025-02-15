package com.other.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.other.app.entity.Permition;
import com.other.app.filter.JwtFilter;
import com.other.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration2 {

	private final UserService userService;
	private final JwtFilter jwtFilter;

	@Bean
	protected SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {
		http.csrf(csrfCustomizer -> csrfCustomizer.disable())
				.sessionManagement(
						sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						requestCustomizer -> requestCustomizer
							.requestMatchers("/app/message/post/**").hasAuthority(Permition.POST_MESSAGE.name())
							.requestMatchers("/app/message/delete/**").hasAuthority(Permition.DELETE_MESSAGE.name())
							.requestMatchers("/app/message/read/**").hasAuthority(Permition.READ_MESSAGE.name())
							.requestMatchers("/app/user/all/**").hasAuthority(Permition.READ_MESSAGE.name())
							.requestMatchers("/app/authenticate/**").permitAll()
							.requestMatchers("/app/registration/**").permitAll())
				.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	protected DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
