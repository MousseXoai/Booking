package org.booking.bookingapp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
//This is client can able to see
@Data
public class ApiException {
    private final String message;
//    private final Throwable throwable;
    private final HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private final ZonedDateTime zonedDateTime;

}
