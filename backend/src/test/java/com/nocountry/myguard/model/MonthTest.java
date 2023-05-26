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


    private OnCall onCall;

    @BeforeAll
    void beforeAll() {
        this.monthWeekend = new Month("May",2023, Type.WEEKEND);
        this.monthWeek = new Month("June",2023, Type.WEEK);
        this.dayMay = LocalDateTime.of(2023,5,20,8,0); //SATURDAY
        this. dayJune = LocalDateTime.of(2023,6,14,8,0); //WEDNESDAY

        try {
            this.onCall = new OnCall(
                    dayMay,
                    LocalDateTime.of(2023,5,20,20,0),
                    monthWeekend);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    void isCorrectDateByMonthTypeTest() {

        assertEquals(DayOfWeek.SATURDAY, dayMay.getDayOfWeek());
        assertEquals(DayOfWeek.WEDNESDAY, dayJune.getDayOfWeek());
        assertEquals(true, monthWeekend.isCorrectDateByMonthType(dayMay));
        assertEquals(true, monthWeek.isCorrectDateByMonthType(dayMay));
        assertEquals(false, monthWeekend.isCorrectDateByMonthType(dayJune));
        assertEquals(true, monthWeek.isCorrectDateByMonthType(dayJune));
    }

    @Test
    void isCorrectOnCallShiftByMonthType() {
        boolean correctShift = onCall.getMonth().isCorrectOnCallShiftByMonthType(onCall);

        assertEquals(true,correctShift);
        assertEquals("day",onCall.getShift());

    }
}