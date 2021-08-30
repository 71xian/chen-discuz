package com.aoexe.discuz.system.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.aoexe.discuz.system.core.web.filter.TokenEntryPoint;
import com.aoexe.discuz.system.core.web.filter.TokenFilter;
import com.aoexe.discuz.system.modular.auth.service.IAuthService;

/**
 * SpringSecurity配置
 *
 * @author chenyuxian
 * @date 2021-08-30
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Resource
	private TokenFilter tokenFilter;

	@Resource
	private TokenEntryPoint tokenEntryPoint;

	@Resource
	private IAuthService authService;

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.cors();

		http.logout().disable();

		http.exceptionHandling().authenticationEntryPoint(tokenEntryPoint);

		http.authorizeRequests()
				.antMatchers("/swagger**/**", "/webjars/**", "/v3/**", "/doc.html", "/login", "/register", "/logout")
				.permitAll();

		http.authorizeRequests().anyRequest().authenticated();

		http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.headers().frameOptions().disable().cacheControl();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
