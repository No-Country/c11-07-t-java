package com.nocountry.myguard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer countHsWeekend;
    private Integer countHsWeek;
    private Integer countOnCall;
    private Long monthId;
    private Long userId;

    @JsonBackReference(value = "MonthCounter")
    @ManyToOne
    @JoinColumn(name = "monthId", insertable = false, updatable = false)
    private Month month;

    @JsonBackReference(value = "CounterUser")
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @JsonCreator
    public Counter(Long userId, Long monthId) {
        this.userId = userId;
        this.monthId = monthId;
        this.countHsWeekend = 0;
        this.countHsWeek = 0;
        this.countOnCall = 0;
    }

    public void addHsWeekend(int duration) {
        if (duration > 0 && duration <= 24) {
            this.countHsWeekend += duration;
        }
    }

    public void addHsWeek(int duration) {
        if (duration > 0 && duration <= 24) {
            this.countHsWeek += duration;
        }

    }

    public void reduceHsWeekend(int duration) {
        if (duration > 0 && duration <= 24) {
            this.countHsWeekend -= duration;
        }
    }
    public void reduceHsWeek(int duration) {
        if (duration > 0 && duration <= 24) {
            this.countHsWeek -= duration;
        }
    }

    public int calculateOnCalls() {
        this.countOnCall = (countHsWeekend + countHsWeek) / 24;
        return this.countOnCall;
    }



}
