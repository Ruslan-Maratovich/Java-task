package com.example.vacationcalculator.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VacationRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}