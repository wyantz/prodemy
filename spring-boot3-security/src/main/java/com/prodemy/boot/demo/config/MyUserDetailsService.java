/**
 * 
 */
package com.prodemy.boot.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prodemy.boot.demo.model.MyUserDetails;
import com.prodemy.boot.demo.model.Role;
import com.prodemy.boot.demo.model.User;
import com.prodemy.boot.demo.model.UserRole;
import com.prodemy.boot.demo.repository.UserRepository;
import com.prodemy.boot.demo.repository.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Awiyanto Ajisasongko
 *
 * Feb 7, 2023
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
	@Autowired private UserRepository userRepo;
	@Autowired private UserRoleRepository userRoleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Mengambil data user
		User user = this.userRepo.findByEmail(username);
		if (user==null) {
			throw new UsernameNotFoundException("User not found with username: "+username);
		}
		
		
		try {
			// Mengambil data UserRole
			List<UserRole> userRoles = this.userRoleRepo.findByIdUserId(username);
			List<Role> roles = new ArrayList<>();
			
			for (UserRole ur : userRoles) {
				Role r = new Role();
				r.setId(ur.getRoleId());
				roles.add(r);
			}
			
			return new MyUserDetails(user, roles);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
