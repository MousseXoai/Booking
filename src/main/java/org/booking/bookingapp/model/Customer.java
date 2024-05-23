package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Data
@Table(name = "customer")
@Entity(
        name = "Customer"
)
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id",
            sequenceName = "customer_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "customer_id"
    )
    @Column(
            name = "customerId",
            nullable = false
    )
    private Integer customerId;
    @Column(
            name = "username",
            nullable = false,
            updatable = false
    )
    private String username;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;
    @Column(
            name = "password",
            nullable = false
    )
    private String password;
    @Column(
            name = "role",
            nullable = false
    )
    private String role;
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
    @Column(
            name = "createAt",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createAt;

    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "customer"
    )
    private List<Booked> booked = new ArrayList<>();

}
