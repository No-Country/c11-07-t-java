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

    private LocalDateTime dayMay;
    private LocalDateTime dayJune;
    private LocalDateTime day2024;


    private OnCall onCallMay;
    private OnCall onCallJune;
    private OnCall onCallWrong;

    @BeforeAll
    void beforeAll() {
        this.monthWeekend = new Month("May", 2023, Type.WEEKEND);
        this.monthWeek = new Month("June", 2023, Type.WEEK);
        this.dayMay = LocalDateTime.of(2023, 5, 20, 8, 0); //SATURDAY
        this.dayJune = LocalDateTime.of(2023, 6, 14, 20, 0); //WEDNESDAY
        this.day2024 = LocalDateTime.of(2024, 6, 14, 20, 0); //WEDNESDAY

        try {
            this.onCallMay = new OnCall(
                    dayMay,
                    LocalDateTime.of(2023, 5, 20, 20, 0),
                    monthWeekend);
            this.onCallJune = new OnCall(
                    dayJune,
                    LocalDateTime.of(2023, 6, 15, 8, 0),
                    monthWeek);
            this.onCallWrong = new OnCall(
                    day2024,
                    LocalDateTime.of(2024, 6, 15, 8, 0),
                    monthWeek);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void isCorrectDateByMonthTypeTest() {

        assertEquals(DayOfWeek.SATURDAY, dayMay.getDayOfWeek());
        assertEquals(DayOfWeek.WEDNESDAY, dayJune.getDayOfWeek());
        assertEquals(true, monthWeekend.isCorrectDayByMonthType(dayMay));
        assertEquals(true, monthWeek.isCorrectDayByMonthType(dayMay));
        assertEquals(false, monthWeekend.isCorrectDayByMonthType(dayJune));
        assertEquals(true, monthWeek.isCorrectDayByMonthType(dayJune));

        assertNull(onCallWrong);
    }

    @Test
    void isCorrectOnCallShiftByMonthTypeTest() {
        boolean correctShiftMay = onCallMay.getMonth().isCorrectOnCallShiftByMonthType(onCallMay.getShift(), onCallMay.getStartDate());
        boolean correctShiftJune = onCallJune.getMonth().isCorrectOnCallShiftByMonthType(onCallJune.getShift(), onCallJune.getStartDate());

        assertEquals("day", onCallMay.getShift());
        assertEquals(true, correctShiftMay);

        assertEquals("night", onCallJune.getShift());
        assertEquals(true, correctShiftJune);


    }
}