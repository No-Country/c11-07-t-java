package com.nocountry.myguard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private Long monthId;

    private Long userId;

    @JsonBackReference(value = "MonthUnavailability")
    @ManyToOne
    @JoinColumn(name = "monthId", insertable = false, updatable = false)
    private Month month;

    @JsonBackReference(value = "UserUnavailability")
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    public Unavailability(LocalDateTime startDate, LocalDateTime endDate, Long monthId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthId = monthId;
    }

    public void calculateEndDate(LocalDateTime startDate, int duration) {
        this.endDate = startDate.plusHours(duration);
    }

    public void calculateDuration(LocalDateTime startDate, LocalDateTime endDate) {
        this.duration = (int) Duration.between(startDate, endDate).toHours();
    }

}

