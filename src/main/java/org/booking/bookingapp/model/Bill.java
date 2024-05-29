package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;
@Data
@Entity(name = "Bill")
@Table(name = "bill")
public class Bill {
    @Id
    @SequenceGenerator(
            name = "bill_id_sequence",
            sequenceName = "bill_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "bill_id_sequence"
    )
    @Column(
            name = "billId",
            updatable = false
    )
    private Long billId;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "userId", referencedColumnName = "user_id"),
            @JoinColumn(name = "roomId", referencedColumnName = "room_id")
    })
    private Booked roomUserBookedBill;

    @Column(
            name = "totalPrice",
            nullable = false
    )
    private Float totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "paymentTypeId",
            referencedColumnName = "paymentTypeId"
    )
    private PaymentType paymentTypeId;

    @Column(
            name = "billName",
            nullable = false
    )
    private String billName;
    @Column(
            name = "billPhoneNumber",
            nullable = false
    )
    private String billPhoneNumber;
    @Column(
            name = "billEmail",
            nullable = false
    )
    private String billEmail;
    @Column(
            name = "billAddress",
            nullable = false
    )
    private String billAddress;
}
