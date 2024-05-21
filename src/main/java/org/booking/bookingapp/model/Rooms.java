package org.booking.bookingapp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "rooms")
@Entity(
        name = "Rooms"
)
public class Rooms {
    @Id
    @SequenceGenerator(
        name = "room_sequence",
        sequenceName = "room_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "room_sequence"
    )
    @Column(
        name = "roomId",
        nullable = false
    )
    private Integer roomId;
    @Column(
            name = "roomName",
            nullable = false
    )
    private String roomName;
    @Column(
            name = "picture",
            nullable = false
    )
    private String picture;
    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(
            name = "price",
            nullable = false
    )
    private Float price;
    @Column(
            name = "status",
            nullable = false
    )
    private boolean status;
    @Column(
            name = "type",
            nullable = false
    )
    private String type;
    @Column(
            name = "size",
            nullable = false
    )
    private Integer size;
    @Column(
            name = "capacity",
            nullable = false
    )
    private Integer capacity;
    @Column(
            name = "bed",
            nullable = false
    )
    private String bed;
    @Column(
            name = "service",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String service;
}
