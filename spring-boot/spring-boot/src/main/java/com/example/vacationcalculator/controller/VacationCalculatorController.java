package com.example.vacationcalculator.controller;

import com.example.vacationcalculator.model.VacationRequest;
import com.example.vacationcalculator.service.VacationCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class VacationCalculatorController {

    private final VacationCalculatorService vacationCalculatorService;

    @GetMapping
    public double calculateVacationPay(@RequestParam double averageSalary,
                                       @RequestParam int vacationDays,
                                       @RequestBody(required = false) VacationRequest vacationRequest) {
        return vacationCalculatorService.calculateVacationPay(averageSalary, vacationDays, vacationRequest);
    }
}