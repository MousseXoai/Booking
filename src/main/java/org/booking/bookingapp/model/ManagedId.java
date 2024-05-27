package org.booking.bookingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ManagedId implements Serializable {
    @Column(name = "manager_id")
    private Long managerId;
    @Column(name = "room_id")
    private Long roomId;
}
