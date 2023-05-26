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

    public boolean isCorrectMonthByOnCallStartDate(LocalDateTime onCallStartDate){
        java.time.Month actualMonth = onCallStartDate.getMonth();

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
    }

    public boolean isCorrectOnCallShiftByMonthType(OnCall onCall){ //TODO Select if the parameter will be an OnCall or a String shift. It depends how is goint to be implemented this method in the class that creates the onCall.
        DayOfWeek day = onCall.getStartDate().getDayOfWeek();

        if (this.getType().equals(Type.WEEKEND)){
            return false;
        } else if (this.getType().equals(Type.WEEK)){
            return true;
        }
        return false;
    }


}
