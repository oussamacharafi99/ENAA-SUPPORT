package com.ENAA_SUPPORT.Config;
import com.ENAA_SUPPORT.Service.UserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    private final UserDetailsImpl userDetailsService;

    public ConfigSecurity(UserDetailsImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/auth/user","/api/auth/technician").hasRole("ADMIN")
                                .requestMatchers("/api/material/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/ticket/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT,"/api/ticket/update_by_admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/ticket/update_by_technician/**").hasRole("TECHNICIAN")
                                .requestMatchers("/api/ticket/**").hasAnyRole("USER", "TECHNICIAN" , "ADMIN")
                                .requestMatchers("/api/ticket/get_technician_tickets/**").hasRole("TECHNICIAN")
                                .requestMatchers("/api/panne/**").hasRole("ADMIN")

                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.disable());
                http.cors(Customizer.withDefaults());
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }


}
