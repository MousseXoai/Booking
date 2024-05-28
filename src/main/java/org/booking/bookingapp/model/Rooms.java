package org.booking.bookingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "rooms")
@Entity(
        name = "Rooms"
)
public class Rooms {
    @Id
    @SequenceGenerator(
        name = "room_id_sequence",
        sequenceName = "room_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "room_id_sequence"
    )
    @Column(
        name = "roomId",
        updatable = false
    )
    private Long roomId;
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
    private String size;
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

    @JsonIgnoreProperties("rooms")
    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "rooms"
    )
    private List<Booked> booked = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "rooms"
    )
    private List<Managed> managed = new ArrayList<>();
}
