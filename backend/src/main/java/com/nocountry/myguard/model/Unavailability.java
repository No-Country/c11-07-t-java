package com.nocountry.myguard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Unavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int duration;

    @ManyToOne
    private Month month;

    @ManyToOne
    private User user;

    public void calculateEndDate(LocalDateTime startDate, int duration) {
        this.endDate = startDate.plusHours(duration);
    }

    public void calculateDuration(LocalDateTime startDate, LocalDateTime endDate) {
        this.duration = (int) Duration.between(startDate, endDate).toHours();
    }

}

