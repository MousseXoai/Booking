package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Data
@Table(name = "users")
@Entity(
        name = "Users"
)
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_id_sequence"
    )
    @Column(
            name = "userId",
            updatable = false
    )
    private Long userId;
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

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "roleId",
            referencedColumnName = "roleId"
    )
    private Role roleId;
    @Column(
            name = "createAt",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createAt;

    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "user"
    )
    private List<Booked> booked = new ArrayList<>();

    @OneToOne(mappedBy = "userId", orphanRemoval = true)
    private Customer customerId;

    @OneToOne(mappedBy = "userId", orphanRemoval = true)
    private Manager managerId;
}
