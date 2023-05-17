package com.nocountry.myguard.model;

import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.enums.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professional { //TODO Extend class User when it is ready
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String lastName;
    String email;
    String enrolment;
    String dni;
    String password; //TODO Change it to class User

    @Enumerated(EnumType.STRING) private Specialization specialization;

    Role role; //TODO Change it to class User
    //@ManyToMany private List<Mes> meses; //TODO Add when class Month is ready
}
