package org.booking.bookingapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.booking.bookingapp.response.PaymentDTOResponse;
import org.booking.bookingapp.response.ResponseObject;
import org.booking.bookingapp.service.payment.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class PaymentControllerV1 {
    private final PaymentService paymentService;
    @GetMapping("/vn-pay")
    public ResponseObject<PaymentDTOResponse.VNPayResponse> pay(HttpServletRequest request, @RequestParam("amount") Long amount){
        return new ResponseObject<>(HttpStatus.OK, "Success", paymentService.createVnPayPayment(request));
    }
    @GetMapping("/vn-pay-callback")
    public ResponseObject<PaymentDTOResponse.VNPayResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return new ResponseObject<>(HttpStatus.OK, "Success", PaymentDTOResponse.VNPayResponse.builder()
                    .code("00")
                    .message("Success")
                    .paymentUrl("")
                    .build());
        } else {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
        }
    }
}
