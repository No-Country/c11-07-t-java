package com.nocountry.myguard.model;

import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int year;

    @Enumerated(EnumType.STRING)
    private Type type; //Week, Weekend/Holiday // feriado no le demos bola todavia

    @ManyToMany(mappedBy = "months") private List<User> users;

    @OneToOne(cascade = CascadeType.ALL) private Counter counter;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL) private List<OnCall> onCalls;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL) private List<Unavailability> unavailabilities;

    public Month(String name, int year, Type type) {
        this.name = name;
        this.year = year;
        this.type = type;
        this.counter = new Counter();
    }

    public boolean isCorrectDateByMonthType(LocalDateTime dateTime){
        DayOfWeek day = dateTime.getDayOfWeek();

        if (this.getType().equals(Type.WEEKEND)){
            return day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY);
        } else if (this.getType().equals(Type.WEEK)){
            return true;
        }

        return false;
    }
}
