package com.zanatahenry.TodoList.security;

import com.zanatahenry.TodoList.enums.UserRoles;
import com.zanatahenry.TodoList.security.jwt.JwtAuthFilter;
import com.zanatahenry.TodoList.security.jwt.JwtService;
import com.zanatahenry.TodoList.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private JwtService jwtService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder () {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public OncePerRequestFilter jwtFilter() {
    return new JwtAuthFilter(jwtService, userService);
  }

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
    return http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/management/**").hasRole(UserRoles.MASTER.toString())
        .requestMatchers("/todo").authenticated()
        .requestMatchers("/**").permitAll())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
      .build();
  }
}
