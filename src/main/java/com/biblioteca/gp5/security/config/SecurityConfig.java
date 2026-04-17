package com.biblioteca.gp5.security.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.biblioteca.gp5.security.filter.AuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final AuthFilter authFilter;

	public SecurityConfig(AuthFilter authFilter) {
		this.authFilter = authFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

						.requestMatchers(HttpMethod.PATCH, "/api/users/me").hasAnyRole("USER", "ALUNO", "ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PATCH, "/api/users/*/role").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PATCH, "/api/users/me/password").hasAnyRole("USER", "ALUNO", "ADMIN")

						.anyRequest().authenticated())

				// Adiciona filtro JWT antes do filtro padrão do Spring Security
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		// Usa BCrypt para criptografar senhas
		return new BCryptPasswordEncoder();

	}

}
