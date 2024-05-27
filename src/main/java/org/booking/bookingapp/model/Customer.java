package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "customer_id_sequence"
    )
    @Column(
            name = "customerId",
            updatable = false
    )
    private Long customerId;
    @Column(
            name = "firstName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "lastName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "address",
            nullable = false
    )
    private String address;
    @Column(
            name = "phoneNumber",
            nullable = false
    )
    private String phoneNumber;
    @Column(
            name = "avatar"
    )
    private String avatar;
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    private Users userId;
}
