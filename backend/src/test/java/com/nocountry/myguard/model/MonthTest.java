package com.nocountry.myguard.model;

import com.nocountry.myguard.enums.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MonthTest {

    private Month monthWeekend;
    private Month monthWeek;

    @BeforeAll
    void beforeAll() {
        this.monthWeekend = new Month("May",2023, Type.WEEKEND);
        this.monthWeek = new Month("June",2023, Type.WEEK);
    }

    @Test
    void isCorrectDateByMonthTypeTest() {

        LocalDateTime dayMay = LocalDateTime.of(2023,5,20,13,0); //SATURDAY
        LocalDateTime dayJune = LocalDateTime.of(2023,6,14,13,0); //WEDNESDAY

        assertEquals(DayOfWeek.SATURDAY, dayMay.getDayOfWeek());
        assertEquals(DayOfWeek.WEDNESDAY, dayJune.getDayOfWeek());
        assertEquals(true, monthWeekend.isCorrectDateByMonthType(dayMay));
        assertEquals(true, monthWeek.isCorrectDateByMonthType(dayMay));
        assertEquals(false, monthWeekend.isCorrectDateByMonthType(dayJune));
        assertEquals(true, monthWeek.isCorrectDateByMonthType(dayJune));
    }
}