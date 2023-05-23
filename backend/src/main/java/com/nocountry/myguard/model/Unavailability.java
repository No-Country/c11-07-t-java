package com.nocountry.myguard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Unavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    LocalDateTime startDate;

    LocalDateTime endDate;

    int duration;

    String shift;

    @ManyToOne
    Month month;


}

