package com.vispractice.fmc.config.cas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.thymeleaf.util.StringUtils;

import com.google.common.base.Preconditions;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgPersonRepository;
import com.vispractice.fmc.web.domain.security.SaltedUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CasUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	@Autowired
	private SysOrgPersonRepository sysOrgPersonRepository;
	
	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		String username = token.getPrincipal().toString();
		SysOrgPerson sysOrgPerson = null;
		
		try {
			sysOrgPerson = sysOrgPersonRepository.getAvailablePersonByLoginName(username);
			Preconditions.checkNotNull(sysOrgPerson);
		} catch (NullPointerException e) {
			log.info("没有找到输入的用户！"+username);
			throw new BadCredentialsException("没有找到输入的用户！");
		}
		if(StringUtils.isEmpty(sysOrgPerson.getFdPassword())){
			log.info("用户口令不能为空！" + username);
			throw new BadCredentialsException("数据库中用户口令为空，不允许登录系统");
		}
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("user"));
        
        Boolean enabled = true; //isFlagTrue(sysOrgPerson.getfde.getEnableFlag());
		Boolean accountNonExpired = true; //isFlagFalse(sysOrgPerson.getExpiredStatus());
		Boolean credentialsNonExpired = true; //isFlagFalse(sysOrgPerson.getCredentialExpiredStatus());
		Boolean accountNonLocked = true; //isFlagFalse(sysOrgPerson.getLockedStatus());

		return new SaltedUser(sysOrgPerson, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
	}

}
