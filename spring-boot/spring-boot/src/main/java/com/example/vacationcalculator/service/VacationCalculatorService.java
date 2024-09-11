package com.example.vacationcalculator.service;

import com.example.vacationcalculator.model.VacationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class VacationCalculatorService {

    private final HolidayService holidayService;
    private static final int WORK_DAYS_PER_MONTH = 21; // Условное количество рабочих дней в месяце

    public double calculateVacationPay(double averageSalary, int vacationDays, VacationRequest vacationRequest) {
        if (vacationRequest != null) {
            vacationDays = adjustForHolidays(vacationRequest.getStartDate(), vacationRequest.getEndDate());
        }

        // Формула расчета отпускных: (Средняя зарплата / кол-во рабочих дней в месяце) * кол-во отпускных дней
        return (averageSalary / WORK_DAYS_PER_MONTH) * vacationDays;
    }

    // Учет праздников и выходных
    private int adjustForHolidays(LocalDate startDate, LocalDate endDate) {
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        int holidays = holidayService.countHolidaysBetween(startDate, endDate);
        return (int) (totalDays - holidays);
    }
}