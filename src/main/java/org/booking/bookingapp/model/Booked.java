package org.booking.bookingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Booked")
@Table(name = "booked")
public class Booked {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @EmbeddedId
    private BookedId bookedId;
    @JsonIgnoreProperties("booked")
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId",
            foreignKey = @ForeignKey(name = "userId_booked_fk")
    )
    private Users user;
    @JsonIgnoreProperties("booked")
    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(
            name = "room_id",
            referencedColumnName = "roomId",
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

}
