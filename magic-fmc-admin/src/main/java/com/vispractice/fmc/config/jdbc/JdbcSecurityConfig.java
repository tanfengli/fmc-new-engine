package com.vispractice.fmc.config.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Profile("jdbcSecurity")
public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService securityService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll().failureUrl("/login?error").permitAll().successForwardUrl("/successHandle").and().logout()
				.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

		ReflectionSaltSource rss = new ReflectionSaltSource();
		rss.setUserPropertyToUse("salt");

		provider.setUserDetailsService(securityService);
		// provider.setSaltSource(rss);
		provider.setPasswordEncoder(md5PasswordEncoder);

		// System.out.println(md5PasswordEncoder.encodePassword("1", "magic"));
		// provider.setMessageSource(messageSource);

		auth.authenticationProvider(provider);
	}

}