package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "Booked")
@Table(name = "booked")
public class Booked {
    @EmbeddedId
    private BookedId bookedId;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(
            name = "customerId",
            foreignKey = @ForeignKey(name = "customerId_booked_fk")
    )
    private Customer customer;
    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(
            name = "roomId",
            foreignKey = @ForeignKey(name = "roomId_booked_fk")
    )
    private Rooms rooms;
    @Column(
            name = "checkIn",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime timeCheckIn;
    @Column(
            name = "checkOut",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime timeCheckOut;

}
