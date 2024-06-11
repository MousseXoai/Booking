package org.booking.bookingapp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
public abstract class PaymentDTOResponse {
    @Builder
    public static class VNPayResponse {
        public String code;
        public String message;
        public String paymentUrl;
    }
}
