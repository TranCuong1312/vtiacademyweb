
package com.vti.academy.web.utils;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vti.academy.web.configuration.security.UserSecurityLogged;

public class SecurityUtils {

	private SecurityUtils() {

	}

	/**
	 * Get information User logged
	 * 
	 * @return userSecurityLogged
	 */
	public static UserSecurityLogged getUserSecurityLogged() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (UserSecurityLogged) authentication.getPrincipal();
	}

	/**
	 * Get information User logged
	 * 
	 * @return userSecurityLogged
	 */
	public static Optional<String> getCurrentUserLogin() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return Optional.ofNullable(((UserSecurityLogged) authentication.getPrincipal()).getUsername());
	}

	/**
	 * If the current user has a specific authority (security role).
	 * <p>
	 * The name of this method comes from the isUserInRole() method in the Servlet
	 * API
	 *
	 * @param authority
	 *            the authority to check
	 * @return true if the current user has the authority, false otherwise
	 */
	public static boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional
				.ofNullable(securityContext.getAuthentication()).map(authentication -> authentication.getAuthorities()
						.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
				.orElse(false);
	}
}
