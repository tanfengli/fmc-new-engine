package com.vispractice.fmc.config.jdbc;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPersonService;
import com.vispractice.fmc.web.domain.security.SaltedUser;

@Service
public class JdbcUserDetailsService implements UserDetailsService{
	@Autowired
	private ISysOrgPersonService sysOrgPersonService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		SysOrgPerson sysOrgPerson = sysOrgPersonService.getAvailablePersonByLoginName(username);
		if (sysOrgPerson == null) {
			throw new UsernameNotFoundException("用户" + username + "没有找到！");
		}

		if(StringUtils.isEmpty(sysOrgPerson.getFdPassword())){
			throw new BadCredentialsException("数据库中用户口令为空，不允许登录系统");
		}
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("user");
		auths.add(authority);

		Boolean enabled = true; //isFlagTrue(sysOrgPerson.getfde.getEnableFlag());
		Boolean accountNonExpired = true; //isFlagFalse(sysOrgPerson.getExpiredStatus());
		Boolean credentialsNonExpired = true; //isFlagFalse(sysOrgPerson.getCredentialExpiredStatus());
		Boolean accountNonLocked = true; //isFlagFalse(sysOrgPerson.getLockedStatus());

		return new SaltedUser(sysOrgPerson, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, auths);
	}
}
