package org.booking.bookingapp.constants;

import java.util.Arrays;
import java.util.List;

public interface SecurityConstants {
    public static final String JWT_KEY = "quanuirhfehvh78222h3fh2398fhfwgeffu2378fh82h8723hf9wevub8w";
    public static final String JWT_HEADER = "Authorization";
    public static final List<String> PERMIT_ALL_ENDPOINTS = Arrays.asList("/register", "/api/authenticate", "/api/v2/rooms");

}
