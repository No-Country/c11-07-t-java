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
public class Professional { //TODO Extend class User when it is ready
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    String lastName;

    String email;

    String enrolment;

    String personalID;

    @Enumerated(EnumType.STRING) private Specialization specialization;

    @ManyToMany private List<Month> months;


    String password; //TODO Change it to class User
    Role role; //TODO Change it to class User

}
