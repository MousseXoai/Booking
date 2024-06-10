package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.BillDTO;
import org.booking.bookingapp.service.bill.IBillService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bill")
@AllArgsConstructor
public class BillControllerV1 {
    private IBillService iBillService;

    @PostMapping("/create")
    public void createBill(BillDTO billDTO){
        System.out.println(billDTO);
        iBillService.createBill(billDTO);
    }
}
