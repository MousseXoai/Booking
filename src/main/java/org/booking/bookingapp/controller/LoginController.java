package org.booking.bookingapp.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.booking.bookingapp.config.UsernamePasswordAuthenProvider;
import org.booking.bookingapp.constants.SecurityConstants;
import org.booking.bookingapp.request.ChangePasswordDTO;
import org.booking.bookingapp.request.ForgotPasswordDTO;
import org.booking.bookingapp.request.RegisterUserDTO;
import org.booking.bookingapp.request.UserLoginDTO;
import org.booking.bookingapp.response.JWTLoginResponse;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequestMapping
@RestController
@AllArgsConstructor
public class LoginController {
    private UsernamePasswordAuthenProvider usernamePasswordAuthenProvider;

    @PostMapping("/login")
    public ResponseEntity<JWTLoginResponse> login(@RequestBody UserLoginDTO customerLoginDTO) {
        Authentication authenticate = usernamePasswordAuthenProvider.authenticate(new UsernamePasswordAuthenticationToken(customerLoginDTO.getEmail(), customerLoginDTO.getPwd()));
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder().issuer("Booking").subject("JWT Token")
                .claim("username", authenticate.getName())
                .claim("authorities", populateAuthorities(authenticate.getAuthorities()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key).compact();

        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstants.JWT_HEADER, jwt);
        JWTLoginResponse jwtLoginResponse = JWTLoginResponse.builder()
                .accessToken(jwt)
                .build();

        return ResponseEntity.ok().headers(headers).body(jwtLoginResponse);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }


}
