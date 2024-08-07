package org.booking.bookingapp.services.bill;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Bill;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.repository.BillRepository;
import org.booking.bookingapp.repository.BookingRepository;
import org.booking.bookingapp.repository.PaymentTypeRepository;
import org.booking.bookingapp.request.BillDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class BillService implements IBillService {
    private PaymentTypeRepository paymentTypeRepository;
    private BookingRepository bookingRepository;
    private BillRepository billRepository;
    @Override
    public MessageResponse createBill(BillDTO billBooked) {
        Bill bill = new Bill();
        try{
            List<Booked> allBookedById = bookingRepository.findAllByBookedId(billBooked.getBillBooked());
            for(Booked booked : allBookedById){
                bill.setBookedId(bookingRepository.findById(booked.getBookedId()).orElseThrow(()-> new NotFoundException("Cannot find booked with id: " + booked.getBookedId() + " yet")));
                Float roomPrice = booked.getRooms().getPrice();
                Duration duration = Duration.between(booked.getTimeCheckIn(), booked.getTimeCheckOut());
                Float totalPrice = duration.toDays() < 1 ? roomPrice : roomPrice * duration.toDays();

                booked.setResponseStatus(2);
                bill.setTotalPrice(totalPrice);
                bill.setPaymentTypeId(paymentTypeRepository.findById(billBooked.getPaymentTypeId()).orElseThrow(()-> new NotFoundException("Cannot find payment type with id: " + billBooked.getPaymentTypeId())));
                bill.setBillName(billBooked.getBillName());
                bill.setBillPhoneNumber(billBooked.getBillPhoneNumber());
                bill.setBillEmail(billBooked.getBillEmail());
                bill.setBillAddress(billBooked.getBillAddress());
                billRepository.save(bill);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return MessageResponse.builder().message("Create bill successfully").statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public List<Bill> getAllBill(Authentication authentication) {
        return billRepository.findByBookedId_UserId(authentication.getName());
    }
}
