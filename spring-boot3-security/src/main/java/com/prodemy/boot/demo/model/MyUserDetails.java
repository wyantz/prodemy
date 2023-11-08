/**
 * 
 */
package com.prodemy.boot.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Awiyanto Ajisasongko
 *
 * Feb 7, 2023
 */
public class MyUserDetails implements UserDetails {
	private static final long serialVersionUID = 7065526406928597677L;
	
	private User user;
	private List<Role> roles;

	public MyUserDetails(User user, List<Role> roles) {
		this.user = user;
		this.roles = roles;
	}
	
	@SuppressWarnings("serial")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> result = new ArrayList<>();
		
		for (Role r : roles) {
			GrantedAuthority ga = new GrantedAuthority() {
				@Override
				public String getAuthority() {
					return r.getId();
				}
			};
			
			result.add(ga);
		}
		return result;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
