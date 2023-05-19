package com.nocountry.myguard.model;

import com.nocountry.myguard.enums.Role;
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
public class Professional extends User { //TODO Extend class User when it is ready

    String name;

    String lastName;

    String email;

    String enrolment;

    String personalID;

    @Enumerated(EnumType.STRING) private Specialization specialization;

    @ManyToMany private List<Month> months;


    public void addMonth(Month month) {
        // Add a month to the Professional List
        this.months.add(month);
        month.getProfessionals().add(this);
    }

    public void removeMonth(Month month) {
        // Remove a month to the Professional List
        this.months.remove(month);
        month.getProfessionals().remove(this);
    }

}
