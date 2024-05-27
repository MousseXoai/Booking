package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "Managed")
@Table(name = "managed")
public class Managed {
    @EmbeddedId
    private ManagedId managedId;
    @ManyToOne
    @MapsId("managerId")
    @JoinColumn(
            name = "manager_id",
            foreignKey = @ForeignKey(name = "manager_id_booked_fk")
    )
    private Manager managers;
    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(
            name = "room_id",
            foreignKey = @ForeignKey(name = "manager_room_id_booked_fk")
    )
    private Rooms rooms;
    @Column(
            name = "changedAt",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime changedAt;
}
