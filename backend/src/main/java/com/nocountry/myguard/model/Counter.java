package com.nocountry.myguard.model;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    private Integer count24Weekend;
    private Integer countHsWeek;
    private Integer count24Week;
    //private Integer monthId;
    //private Integer userId;

    @ManyToOne
    //@JoinColumn(name = "monthId", insertable = false, updatable = false)
    private Month month;

    @ManyToOne
    //@JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @JsonCreator
    public Counter(Month month, User user) {
        this.month = month;
        this.user = user;
    }


}
