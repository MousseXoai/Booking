package org.booking.bookingapp.constants;

import java.util.Arrays;
import java.util.List;

public interface SecurityConstants {
    String JWT_KEY = "quanuirhfehvh78222h3fh2398fhfwgeffu2378fh82h8723hf9wevub8w";
    String JWT_HEADER = "Authorization";
    List<String> PERMIT_ALL_ENDPOINTS = Arrays.asList("/register", "/api/authenticate", "/api/v2/rooms/**","/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html");

}
