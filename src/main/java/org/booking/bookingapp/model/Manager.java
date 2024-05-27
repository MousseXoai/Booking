package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "Manager")
@Table(name = "manager")
public class Manager {
    @Id
    @SequenceGenerator(
            name = "manager_id_sequence",
            sequenceName = "manager_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "manager_id_sequence"
    )
    @Column(
            name = "managerId",
            updatable = false
    )
    private Long managerId;
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

    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "managers"
    )
    private List<Managed> managed = new ArrayList<>();
}
