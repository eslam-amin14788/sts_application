package com.assessment.sts.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.assessment.sts.config.LocalDateTimeDeserializer;
import com.assessment.sts.config.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    @NotNull
    private Long patientId;

    private String status;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private LocalDateTime reservationDate;

    private String cancelReason;

   
}
