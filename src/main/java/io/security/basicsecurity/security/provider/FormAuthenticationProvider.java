package io.security.basicsecurity.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.security.basicsecurity.security.common.FormWebAuthenticationDetails;
import io.security.basicsecurity.security.service.AccountContext;

public class FormAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public FormAuthenticationProvider(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

		if(!passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
			throw new BadCredentialsException("Invalid Password");
//      throw new BadCredentialsException("BadCredentialsException");
		}

		String secretKey = ( (FormWebAuthenticationDetails) authentication.getDetails() ).getSecretKey();
//    FormWebAuthenticationDetails formWebAuthenticationDetails = (FormWebAuthenticationDetails) authentication.getDetails();
//    String secretKey = formWebAuthenticationDetails.getSecretKey();

		if(secretKey == null || ! secretKey.equals("secret")) {
//    if(secretKey == null || !"secret".equals(secretKey)) {
			throw new IllegalArgumentException("invalid Secret");
//      throw new InsufficientAuthenticationException("InsufficientAuthenticationException");
		}

		return new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
//    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
//    return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
