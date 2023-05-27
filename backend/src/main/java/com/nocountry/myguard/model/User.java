package com.nocountry.myguard.model;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Counter> counters;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    //Professional methods
    public void addSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }


    public void addMonth(Month month) {
        // Add a month to the Professional List
        this.months.add(month);
        month.getUsers().add(this);
    }

    public void removeMonth(Month month) {
        // Remove a month to the Professional List
        this.months.remove(month);
        month.getUsers().remove(this);
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
