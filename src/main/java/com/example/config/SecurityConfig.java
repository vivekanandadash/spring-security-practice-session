package com.example.config;

import com.example.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
public class SecurityConfig {

    @Bean
  public SecurityFilterChain filterChain(HttpSecurity http){
      http.csrf(csrf->csrf.disable())
              .authorizeHttpRequests(req->{
                  req.requestMatchers("/api/v1/employee/patient_signup","/api/v1/employee/doctor_signup","/api/v1/employee/login").permitAll()
                          .requestMatchers("/api/v1/patient").hasAnyRole("PATIENT","DOCTOR")
                          .requestMatchers("/api/v1/doctor").hasRole("DOCTOR")
                          .anyRequest().authenticated();
              }).httpBasic(Customizer.withDefaults());
      return http.build();
  }
  @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
  }
  @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
  }
  @Bean
    public AuthenticationProvider authenticationProvider(
    CustomerUserDetailsService userDetailsService,
    PasswordEncoder passwordEncoder
  ){
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
      authenticationProvider.setPasswordEncoder(passwordEncoder);
    return authenticationProvider;
  }
}
