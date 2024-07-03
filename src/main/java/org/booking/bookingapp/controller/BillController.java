package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Bill;
import org.booking.bookingapp.request.BillDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.services.bill.IBillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
@AllArgsConstructor
public class BillController {
    private IBillService iBillService;

    @GetMapping("/history")
    public ResponseEntity<List<Bill>> getAllBill(Authentication authentication){
        return ResponseEntity.ok().body(iBillService.getAllBill(authentication));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createBill(BillDTO billDTO){
        MessageResponse messageResponse = iBillService.createBill(billDTO);
        return ResponseEntity.ok(messageResponse);
    }
}
