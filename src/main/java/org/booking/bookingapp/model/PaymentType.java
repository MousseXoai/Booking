package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "PaymentType")
@Table(name = "paymentType")
public class PaymentType {
    @Id
    @SequenceGenerator(
            name = "payment_type_id_sequence",
            sequenceName = "payment_type_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "payment_type_id_sequence"
    )
    @Column(
            name = "paymentTypeId",
            updatable = false
    )
    private Integer paymentTypeId;
    @Column(
            name = "paymentTypeName",
            nullable = false
    )
    private String paymentTypeName;
}
