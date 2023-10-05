package com.zanatahenry.TodoList.security.jwt;

import com.zanatahenry.TodoList.services.impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserServiceImpl userService;

  public JwtAuthFilter(JwtService jwtService, UserServiceImpl userService) {
    this.jwtService = jwtService;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorization = request.getHeader("Authorization");
    if (authorization != null && authorization.startsWith("Bear")) {
      String token = authorization.split(" ")[1];
      boolean isValid = jwtService.isValid(token);

      if (isValid) {
        String emailUser = jwtService.getUserEmail(token);
        UserDetails loadedUser = userService.loadUserByEmail(emailUser);
        UsernamePasswordAuthenticationToken user = new
            UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
        user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(user);
      }
    }

    filterChain.doFilter(request, response);
  }
}
