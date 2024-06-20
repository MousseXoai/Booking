package org.booking.bookingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "Feedback")
@Table(name = "feedback")
public class Feedback {
    @Id
    @SequenceGenerator(
            name = "feedback_id_sequence",
            sequenceName = "feedback_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "feedback_id_sequence"
    )
    @Column(
            name = "feedbackId",
            updatable = false
    )
    private Long feedbackId;

    @Column(
            name = "content",
            nullable = false
    )
    private String content;
    @Column(
            name = "rating",
            nullable = false
    )
    private Float rating;

    @JsonIgnoreProperties("feedback")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "room_id",
            referencedColumnName = "roomId"
    )
    private Rooms room;
}
