package org.booking.bookingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Booked")
@Table(name = "booked")
public class Booked {
    @Id
    @SequenceGenerator(
            name = "booked_id_sequence",
            sequenceName = "booked_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "booked_id_sequence"
    )
    @Column(
            name = "bookedId",
            updatable = false
    )
    private Long bookedId;

    @JsonIgnoreProperties("booked")
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "userId_booked_fk")
    )
    private Users user;

    @JsonIgnoreProperties("booked")
    @ManyToOne
    @JoinColumn(
            name = "room_id",
            foreignKey = @ForeignKey(name = "roomId_booked_fk")
    )
    private Rooms rooms;

    @Column(
            name = "createdAt",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

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

    @Column(
            name = "responseStatus",
            nullable = false
    )
    private Integer responseStatus;

}
