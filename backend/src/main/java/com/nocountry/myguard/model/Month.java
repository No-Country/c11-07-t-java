package com.nocountry.myguard.model;

import com.nocountry.myguard.enums.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    int year;

    String type; //Week, Weekend/Holiday

    @Enumerated(EnumType.STRING) private List<Specialization> specializations; //TODO Review: this is placed according to the UML, we are going to divide Month by Specialization?

    @ManyToMany(mappedBy = "months") private List<Professional> professionals;

    @OneToOne(cascade = CascadeType.ALL) private Counter counter;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL) private List<OnCall> onCalls;

    //@OneToMany(mappedBy = "month", cascade = CascadeType.ALL) private List<Unavailability> unavailabilities; TODO Add when class Unavailavility is ready
}
