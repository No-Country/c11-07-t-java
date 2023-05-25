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
    private Long id;

    private String name;

    private int year;

    private String type; //Week, Weekend/Holiday // feriado no le demos bola todavia

    @ManyToMany(mappedBy = "months") private List<User> users;

    @OneToOne(cascade = CascadeType.ALL) private Counter counter;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL) private List<OnCall> onCalls;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL) private List<Unavailability> unavailabilities;
}
