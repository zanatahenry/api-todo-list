package com.zanatahenry.TodoList.security;

import com.zanatahenry.TodoList.enums.UserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder () {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
    return http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/management/**").hasRole(UserRoles.MASTER.toString())
        .requestMatchers("/**").permitAll())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService () {
    UserDetails user = User.builder()
      .username("teste")
      .password(passwordEncoder().encode("123"))
      .roles(UserRoles.USER.toString())
      .build();

    return new InMemoryUserDetailsManager(user);
  }

}
