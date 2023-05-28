package com.nocountry.myguard.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nocountry.myguard.enums.Specialization;
import com.nocountry.myguard.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Year;
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

    @Enumerated(EnumType.STRING)
    private Type type; //Week, Weekend/Holiday // feriado no le demos bola todavia

    //@ManyToMany(mappedBy = "months") private List<User> users;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL)
    private List<Counter> counters;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL)
    private List<OnCall> onCalls;

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL)
    private List<Unavailability> unavailabilities;

    @JsonCreator
    public Month(String name, int year, Type type) {
        this.name = name;
        this.year = year;
        this.type = type;
    }

    public boolean isCorrectDayByMonthType(LocalDateTime dateTime){

        if (this.getType().equals(Type.WEEKEND)){
            return this.isWeekend(dateTime);
        } else if (this.getType().equals(Type.WEEK)){
            return true;
        }

        return false;
    }

    public boolean isCorrectMonthByOnCallStartDate(LocalDateTime onCallStartDate){
        int actualYear = onCallStartDate.getYear();
        java.time.Month actualMonth = onCallStartDate.getMonth();

        if (this.year == actualYear){
            switch (actualMonth){
                case JANUARY -> {
                    return this.name.equals("January");
                }
                case FEBRUARY -> {
                    return this.name.equals("February");
                }
                case MARCH -> {
                    return this.name.equals("March");
                }
                case APRIL -> {
                    return this.name.equals("April");
                }
                case MAY -> {
                    return this.name.equals("May");
                }
                case JUNE -> {
                    return this.name.equals("June");
                }
                case JULY -> {
                    return this.name.equals("July");
                }
                case AUGUST -> {
                    return this.name.equals("August");
                }
                case SEPTEMBER -> {
                    return this.name.equals("September");
                }
                case OCTOBER -> {
                    return this.name.equals("October");
                }
                case NOVEMBER -> {
                    return this.name.equals("November");
                }
                case DECEMBER -> {
                    return this.name.equals("December");
                }
                default -> {
                    return false;
                }
            }
        } else {
            return false;
        }

    }

    public boolean isCorrectOnCallShiftByMonthType(String onCallShift, LocalDateTime onCallStartDate){

        if (this.getType().equals(Type.WEEK)){
            if (this.isWeekend(onCallStartDate)){
                return true;
            } else {
                return onCallShift.equals("night")? true : false;
            }
        } else if (this.getType().equals(Type.WEEKEND)){
            return true;
        }

        return false;
    }

    private boolean isWeekend(LocalDateTime dateTime){
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }
}
