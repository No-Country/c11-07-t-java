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
public class OnCall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    LocalDateTime startDate;

    LocalDateTime endDate;

    int duration; // quantity of hours endDate - startDate

    String shift; //day - night

    @ManyToOne
    Month month;

}
