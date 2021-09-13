package com.assessment.sts.dto;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO {


    @NotNull
    private String name;
    
    private String mobile;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private LocalDate birthDate;


   
}
