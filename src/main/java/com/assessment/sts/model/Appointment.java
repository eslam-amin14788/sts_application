package com.assessment.sts.model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.assessment.sts.config.LocalDateTimeDeserializer;
import com.assessment.sts.config.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "appointment")
@Entity
@Data
public class Appointment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "status")
    private String status;

    @Column(name = "reservation_date")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime reservationDate;

    @Column(name = "cancel_reason")
    private String cancelReason;
    
    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "patient_id")
    private Patient patient;

  
}
