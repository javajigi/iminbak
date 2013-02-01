package net.slipp.social.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.slipp.domain.user.SocialUser;
import net.slipp.support.security.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;

public class SlippSecurityAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	public final static String DEFAULT_AUTHENTICATION_URL = "/authenticate";

	protected SlippSecurityAuthenticationFilter() {
		super(DEFAULT_AUTHENTICATION_URL);
	}
	
	@Autowired
	private SessionService sessionService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	@Autowired(required = false)
	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		super.setRememberMeServices(rememberMeServices);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if(sessionService.isAuthenticated()) {
			return sessionService.getAuthentication();
		} else {
			SocialUser signInDetails = (SocialUser) request.getSession().getAttribute(
					SlippSecuritySignInAdapter.SIGN_IN_DETAILS_SESSION_ATTRIBUTE_NAME);
			UserDetails userDetails = userDetailsService.loadUserByUsername(signInDetails.getUserId());
			return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
					userDetails.getPassword(), userDetails.getAuthorities());			
		}
	}
}
