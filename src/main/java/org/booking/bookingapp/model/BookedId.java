package org.booking.bookingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookedId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "room_id")
    private Long roomId;
//    @Column(name = "createdAt")
//    private LocalDateTime createdAt;
}
