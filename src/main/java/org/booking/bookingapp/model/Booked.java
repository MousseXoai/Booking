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
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "userId_booked_fk")
    )
    private Users user;
    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(
            name = "room_id",
            foreignKey = @ForeignKey(name = "roomId_booked_fk")
    )
    private Rooms rooms;
    @Column(
            name = "createdAt",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;
    @Column(
            name = "responseStatus",
            nullable = false
    )
    private Integer responseStatus;

//    @OneToOne(
//            cascade = CascadeType.ALL,
//            mappedBy = "userBookedBill"
//    )
//    @JoinColumn(
//            name = "userId",
//            referencedColumnName = "userId"
//    )
//    private Bill userBooked;
//    @OneToMany(
//            cascade = CascadeType.ALL,
//            mappedBy = "roomBookedBill"
//    )
//    private List<Bill> roomBooked;

}
