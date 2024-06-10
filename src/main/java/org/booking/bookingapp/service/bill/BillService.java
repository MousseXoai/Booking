package org.booking.bookingapp.service.bill;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Bill;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.repository.BillRepository;
import org.booking.bookingapp.repository.BookingRepository;
import org.booking.bookingapp.repository.PaymentTypeRepository;
import org.booking.bookingapp.request.BillDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
@Service
@AllArgsConstructor
public class BillService implements IBillService {
    private PaymentTypeRepository paymentTypeRepository;
    private BookingRepository bookingRepository;
    private BillRepository billRepository;
    @Override
    public void createBill(BillDTO billBooked) {
        Bill bill = new Bill();
        System.out.println(billBooked);
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
    }
}
