package org.booking.bookingapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("statusCode")
    private Integer statusCode;
}
