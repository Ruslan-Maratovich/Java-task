package com.example.vacationcalculator.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class HolidayService {

    // Мок данные - можно заменить на получение из базы или API
    private static final List<LocalDate> HOLIDAYS = List.of(
            LocalDate.of(2024, 1, 1), // Новый год
            LocalDate.of(2024, 5, 1)  // День труда
    );

    public int countHolidaysBetween(LocalDate start, LocalDate end) {
        int holidayCount = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            if (isWeekendOrHoliday(date)) {
                holidayCount++;
            }
        }
        return holidayCount;
    }

    private boolean isWeekendOrHoliday(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                HOLIDAYS.contains(date);
    }
}