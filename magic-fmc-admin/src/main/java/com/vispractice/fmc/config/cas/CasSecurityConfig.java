package com.vispractice.fmc.config.cas;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@Profile("casSecurity")
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法验证
public class CasSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CasProperties casProperties;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.authenticationProvider(casAuthenticationProvider());
	}

	/** 定义安全策略 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.failureUrl("/login?error").permitAll().successForwardUrl("/successHandle").and().logout().permitAll();

		http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint()).and()
				.addFilter(casAuthenticationFilter()).addFilterBefore(casLogoutFilter(), LogoutFilter.class)
				.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);

		// http.csrf().disable(); //禁用CSRF
	}

	/** 认证的入口 */
	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(casProperties.getCasServerLoginUrl());
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		
		return casAuthenticationEntryPoint;
	}

	/** 指定service相关信息 */
	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(casProperties.getAppServerUrl() + casProperties.getAppLoginUrl());
		serviceProperties.setAuthenticateAllArtifacts(true);
		
		return serviceProperties;
	}

	/** CAS认证过滤器 */
	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		casAuthenticationFilter.setFilterProcessesUrl(casProperties.getAppLoginUrl());
		// casAuthenticationFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(casProperties.getAppLogoutUrl()));
		
		return casAuthenticationFilter;
	}

	/** CAS 认证 Provider */
	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
		// casAuthenticationProvider.setUserDetailsService(customUserDetailsService());
		// //这里只是接口类型，实现的接口不一样，都可以的。
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setKey("casAuthenticationProviderKey1");
		return casAuthenticationProvider;
	}

	/** 用户自定义的AuthenticationUserDetailsService */
	@Bean
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
		return new CasUserDetailsService();
	}

	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		return new Cas20ServiceTicketValidator(casProperties.getCasServerUrl());
	}

	/** 单点登出过滤器 */
	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setCasServerUrlPrefix(casProperties.getCasServerUrl());
		singleSignOutFilter.setIgnoreInitConfiguration(true);
		
		return singleSignOutFilter;
	}

	/** 请求单点退出过滤器 */
	@Bean
	public LogoutFilter casLogoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter(casProperties.getCasServerLogoutUrl(),new SecurityContextLogoutHandler());
		logoutFilter.setFilterProcessesUrl(casProperties.getAppLogoutUrl());
		
		return logoutFilter;
	}
}
