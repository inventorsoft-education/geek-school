package com.geekschool.config.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class DefaultAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final String HOME_PAGE = "/";

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
		log.debug("Authentication success handler is invoked");
		response.sendRedirect(HOME_PAGE);
	}
}
