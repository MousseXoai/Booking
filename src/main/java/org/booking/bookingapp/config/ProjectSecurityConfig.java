package org.booking.bookingapp.config;

import lombok.RequiredArgsConstructor;
import org.booking.bookingapp.constants.SecurityConstants;
//import org.booking.bookingapp.filter.CsrfCookieFilter;
import org.booking.bookingapp.filter.JWTTokenGeneratorFilter;
import org.booking.bookingapp.filter.JWTTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class ProjectSecurityConfig {
    private final LogoutHandler logoutHandler;

    public static final String[] OPEN_APIS_DOC = {
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        requestHandler.setCsrfRequestAttributeName("_csrf");

            http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//                    config.setAllowedMethods(Collections.singletonList("*"));
//                    config.setAllowCredentials(true);
//                    config.setAllowedHeaders(Collections.singletonList("*"));
//                    config.setExposedHeaders(Arrays.asList("Authorization"));
//                    config.setMaxAge(3600L);
//                    return config;
//                }))
//                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers( "/register","/api/logout", "/api/authenticate","/api/v1/customer/create", "api/v1/manager/create", "/api/v2/rooms/add", "/api/v1/booking", "/api/v1/booking/**", "/api/v1/bill/create", "/get-otp", "/change-password", "/ban-user")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)

                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers(OPEN_APIS_DOC).permitAll()
                        .requestMatchers("/api/v1/booking/**", "/api/v1/bill/**","/api/v1/payment/**", "/api/v1/feedback/**").hasAnyRole("CUSTOMER","MANAGER")
                        .requestMatchers("/api/v1/customer/**").hasRole("CUSTOMER")
                        .requestMatchers("/api/v1/manager/**").hasRole("MANAGER")
                        .requestMatchers("/api/v1/users/profile").authenticated()
                        .requestMatchers("/api/v1/users/register", "/login", "/api/v1/rooms/**", "/api/v1/users/get-otp", "/api/v1/users/change-password", "/api/v1/users/forgot-password").permitAll())
                    .logout(logout -> logout
                        .logoutUrl("/api/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> {
                            SecurityContextHolder.clearContext();
                            response.setStatus(200);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write("{\"message\":\"Logged out successfully\"}");
                        })
                    );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
