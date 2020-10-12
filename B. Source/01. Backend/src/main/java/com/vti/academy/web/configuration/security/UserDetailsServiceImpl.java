package com.vti.academy.web.configuration.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vti.academy.web.model.User;
import com.vti.academy.web.service.AccountService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountService accountServiceRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = accountServiceRepo.findByUserName(username);
		
		User user = optional.orElseThrow(() -> new UsernameNotFoundException("No user " +
        		  								"Found with username : " + username));
		
		
//		List<GrantedAuthority> grantList = new ArrayList<>();
//		UserSecurityLogged userSecurity = new UserSecurityLogged(user.getUsername(), user.getPassword(), mapRoleToAuthority(grantList));
//		return userSecurity;
		return new org.springframework.security
                .core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true,
                true, getAuthorities(user.getRole().toString()));
	}

	public UserDetails loadUserByToken(JSONObject payload) throws UsernameNotFoundException {
		Optional<User> optional = accountServiceRepo.findByDetailToken(payload);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(payload.getString("username") + " not found !");
		}
		
		User user = optional.get();
		
//		List<GrantedAuthority> grantList = new ArrayList<>();	
//		UserSecurityLogged userSecurity = new UserSecurityLogged(user.getUsername(), user.getPassword(), mapRoleToAuthority(grantList));
//		return userSecurity;
		return new org.springframework.security
        .core.userdetails.User(user.getUsername(), user.getPassword(),
        true, true, true,
        true, getAuthorities(user.getRole().toString()));
	}

	/**
	 * 
	 * @param grantList
	 * @param role
	 */
//	private List<GrantedAuthority> mapRoleToAuthority(List<GrantedAuthority> grantList) {
//		GrantedAuthority authority = new SimpleGrantedAuthority(Role.ADMIN.toString());
//		grantList.add(authority);
//		
//		return grantList;
//	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
    
	
}
