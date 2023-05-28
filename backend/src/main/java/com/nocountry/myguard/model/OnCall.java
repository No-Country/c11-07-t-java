package com.nocountry.myguard.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnCall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int duration; // quantity of hours endDate - startDate

    private String shift; //day - night

    @ManyToOne
    private Month month;

    @JsonCreator
    public OnCall(LocalDateTime startDate, LocalDateTime endDate, Month month) throws Exception{
        calculateShift(startDate);

        if (month.isCorrectMonthByOnCallStartDate(startDate) &&
                month.isCorrectOnCallShiftByMonthType(this.getShift(),startDate)){
            this.startDate = startDate;
            this.endDate = endDate;
            this.month = month;
            calculateDuration(startDate,endDate);

        } else {
            throw new Exception("Incorrect month assigned by start date");
        }

    }

    public void calculateEndDate(LocalDateTime startDate, int duration) {
        this.endDate = startDate.plusHours(duration);
    }

    public void calculateDuration(LocalDateTime startDate, LocalDateTime endDate) {
        this.duration = (int) Duration.between(startDate, endDate).toHours();
    }

    public void calculateShift(LocalDateTime startDate) {

        LocalTime startTime = startDate.toLocalTime();

        LocalTime nightShiftStart = LocalTime.of(19, 59);
        LocalTime dayShiftEnd = LocalTime.of(8, 0);

        if (startTime.isAfter(nightShiftStart) || startTime.isBefore(dayShiftEnd)) {
            this.shift = "night";
        } else {
            this.shift = "day";
        }
    }

}
