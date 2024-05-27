package org.booking.bookingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class BookedId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "room_id")
    private Long roomId;
}
