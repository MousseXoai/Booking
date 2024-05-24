package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "Role")
@Table(name = "role")
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_id_sequence",
            sequenceName = "role_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "role_id_sequence"
    )
    @Column(
            name = "roleId",
            updatable = false
    )
    private Integer roleId;
    @Column(
            name = "roleName",
            nullable = false
    )
    private String roleName;

    @OneToOne(mappedBy = "roleId")
    private Users users;
}
