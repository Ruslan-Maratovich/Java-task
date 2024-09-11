package com.example.vacationcalculator;

import com.example.vacationcalculator.service.VacationCalculatorService;
import com.example.vacationcalculator.model.VacationRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VacationCalculatorServiceTest {

	@Mock
	private HolidayService holidayService;

	@InjectMocks
	private VacationCalculatorService vacationCalculatorService;

	@Test
	public void testCalculateVacationPayWithoutHolidays() {
		double averageSalary = 120000;
		int vacationDays = 10;

		double result = vacationCalculatorService.calculateVacationPay(averageSalary, vacationDays, null);

		assertEquals(57142.86, result, 0.01);
	}

	@Test
	public void testCalculateVacationPayWithHolidays() {
		double averageSalary = 120000;
		VacationRequest vacationRequest = new VacationRequest();
		vacationRequest.setStartDate(LocalDate.of(2024, 5, 1)); // 1 мая - праздник
		vacationRequest.setEndDate(LocalDate.of(2024, 5, 10));

		double result = vacationCalculatorService.calculateVacationPay(averageSalary, 10, vacationRequest);

		// Допустим, один праздник и один выходной
		assertEquals(45714.29, result, 0.01);
	}
}