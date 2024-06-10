package org.booking.bookingapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booking.bookingapp.model.Booked;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private List<Long> billBooked;
    private Integer paymentTypeId;
    private String billName;
    private String billPhoneNumber;
    private String billEmail;
    private String billAddress;
}
