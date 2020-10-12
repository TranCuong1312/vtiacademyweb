package com.vti.academy.web.configuration;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vti.academy.web.configuration.security.UserDetailsServiceImpl;
import com.vti.academy.web.utils.Utils;

import io.jsonwebtoken.ExpiredJwtException;

import org.json.JSONObject;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsServiceImpl jwtUserDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
			
		String jwtToken = null;
		JSONObject payload = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				//split into 3 parts with . delimiter
				String[] parts = jwtToken.split("\\.");
				payload = new JSONObject(Utils.decode(parts[1]));
				
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
			if(payload != null) {
				String username = payload.getString("sub");
		        if (!payload.has("exp")) {
		        	System.out.println("Payload doesn't contain expiry " + payload);
		        }
		        // Once we get the token validate it.
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.jwtUserDetailsService.loadUserByToken(payload);
					// if token is valid configure Spring Security to manually set
					// authentication
					if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							// After setting the Authentication in the context, we specify
							// that the current user is authenticated. So it passes the
							// Spring Security Configurations successfully.
							SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}
			}
			else {
				System.out.println("Payload is Empty");
			}		
		} else {
			if(request.getServletPath().contains("/public/") || request.getServletPath().contains("/auth/")) {
				
			}
			else {
				logger.warn("JWT Token does not begin with Bearer String");
			}
		}
		
		chain.doFilter(request, response);
	}
	
}
