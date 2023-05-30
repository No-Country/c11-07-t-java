package com.nocountry.myguard.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nocountry.myguard.enums.Role;
import com.nocountry.myguard.enums.Specialization;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;

    private String lastName;

    private String enrolment;

    private String personalID;

    @Enumerated(EnumType.STRING) private Specialization specialization;

    //@ManyToMany private List<Month> months;
    @JsonManagedReference(value = "CounterUser")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Counter> counters;

    @JsonManagedReference(value = "UserOnCall")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OnCall> onCalls;

    @JsonManagedReference(value = "UserUnavailability")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Unavailability> unavailabilities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    //Professional methods
    public void addSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    //UserDetails methods


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
