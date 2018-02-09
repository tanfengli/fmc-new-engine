package com.vispractice.fmc.web.domain.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;

public class SaltedUser extends User {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	private SysOrgPerson sysOrgPerson;

	private String salt;
	
	/**
	 * 生成加盐用户
	 * @param sysOrgPerson
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public SaltedUser(SysOrgPerson sysOrgPerson,  boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,Collection<? extends GrantedAuthority> authorities){
		
		super(sysOrgPerson.getFdLoginName(), sysOrgPerson.getFdPassword(),  enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.setSysOrgPerson(sysOrgPerson);
		
		this.setSalt("magic");
//		this.setSalt(sysOrgPerson.getSalt());

	}

	public SysOrgPerson getSysOrgPerson() {
		return sysOrgPerson;
	}

	public void setSysOrgPerson(SysOrgPerson sysOrgPerson) {
		this.sysOrgPerson = sysOrgPerson;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
