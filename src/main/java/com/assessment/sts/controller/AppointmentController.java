package com.assessment.sts.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.sts.constants.CancelReasonEnum;
import com.assessment.sts.dto.AppointmentDTO;
import com.assessment.sts.dto.PatientDTO;
import com.assessment.sts.model.Appointment;
import com.assessment.sts.model.Patient;
import com.assessment.sts.service.AppointmentService;

import io.swagger.annotations.ApiOperation;

@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	@ApiOperation(notes = "", value = "API to get patient profile data by name")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/patient/profile/byName")
	public Patient getPatientProfileByName(@RequestParam String patientName) {
		return appointmentService.getPatientProfileByName(patientName);
	}
	
	@ApiOperation(notes = "patient date of birth format should be yyyy-MM-dd", value = "API to create new patient profile")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add/new/patient")
	public Long createNewPatientProfile(@RequestBody @Valid PatientDTO patientDTO) {
		return appointmentService.createNewPatientProfile(patientDTO);
	}
	
	@ApiOperation(notes = "should entered  patient name in specific and unique way and date format should be yyyy-MM-dd HH:mm:ss", value = "API to create new appointment")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add/new/appointment")
	public Long createNewReservation(@RequestBody @Valid AppointmentDTO appointmentDTO) {
		return appointmentService.createNewReservation(appointmentDTO);
	}
	
	
	@ApiOperation(notes = "", value = "API to get all configured cancelation reasons and this can help in using drop down list for selection the cancelation reason")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/get/cancel/reasons")
	public List<CancelReasonEnum> getAllConfiguredCancelReason() {
		return appointmentService.getAllConfiguredCancelReason();
	}
	
	@ApiOperation(notes = "", value = "API to cancel appointment with reason")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/cancel/{appointmentId}")
	public void cancelReservation(@PathVariable Long appointmentId, @RequestBody String cancelReason) {
		appointmentService.cancelReservation(appointmentId, cancelReason);
	}

	@ApiOperation(notes = "", value = "API to get daily appointments")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/daily/appointments")
	public List<Appointment> getDailyAppointment() {
		return appointmentService.getDailyAppointment();
		
	}

	@ApiOperation(notes = "date format should be yyyy-MM-dd", value = "API to get all appointments in specific date")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("appointments/by/date")
	public List<Appointment> getAllAppointmentsByDate(@RequestParam @NotBlank String date) {
		return appointmentService.getAllAppointmentsByDate(LocalDate.parse(date));
	}
	
	@ApiOperation(notes = "", value = "API to get patient history by name")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/appointment/history/byName")
	public List<Appointment> getAppointmentHistoryByName(@RequestParam String patientName) {
		return appointmentService.getAppointmentHistoryByName(patientName);
	}
	
	@ApiOperation(notes = "", value = "API to get patient history by id")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/appointment/history/byId")
	public List<Appointment> getAppointmentHistoryById(@RequestParam Long patientId) {
		return appointmentService.getAppointmentHistoryById(patientId);
	}    
	
}
