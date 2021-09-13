package com.assessment.sts.service;

import com.assessment.sts.constants.CancelReasonEnum;
import com.assessment.sts.constants.StatusEnum;
import com.assessment.sts.dto.AppointmentDTO;
import com.assessment.sts.dto.PatientDTO;
import com.assessment.sts.model.Appointment;
import com.assessment.sts.model.Patient;
import com.assessment.sts.repository.AppointmentRepository;
import com.assessment.sts.repository.PatientRepository;

import org.modelmapper.ModelMapper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientProfileByName(String patientName) {
    	 Patient patient=patientRepository.findByNameIgnoreCase(patientName).orElse(new Patient());
    	 return patient;
    }
    
    public Long createNewPatientProfile(PatientDTO patientDTO) {
    	Patient patient = modelMapper.map(patientDTO,Patient.class);
    	patientRepository.save(patient);
        return patient.getId();
    }
    
    public Long createNewReservation(AppointmentDTO appointmentDTO) {
        Appointment appointment = modelMapper.map(appointmentDTO,Appointment.class);
        appointment.setStatus(StatusEnum.ACTIVE.toString());
        appointmentRepository.save(appointment);
        return appointment.getId();
    }
    
    public List<CancelReasonEnum> getAllConfiguredCancelReason(){
    	return Arrays.asList(CancelReasonEnum.values());
    }
    
    public List<Appointment> getDailyAppointment() {
        List<Appointment> appointments = appointmentRepository.findAllByReservationDateBetween(LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0, 0)),LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)));
        return appointments;
    }

    public void cancelReservation(Long appointmentId , String cancelReason) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(IllegalArgumentException::new);
        appointment.setStatus(StatusEnum.CANCEL.toString());
        appointment.setCancelReason(cancelReason);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsByDate(LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findAllByReservationDateBetween(LocalDateTime.of(date, LocalTime.of(0,0, 0)),LocalDateTime.of(date, LocalTime.of(23,59,59)));;
        return appointments;
    }

    
    public List<Appointment> getAppointmentHistoryById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(IllegalArgumentException::new);
        return patient.getAppointments();
    }


    public List<Appointment> getAppointmentHistoryByName(String patientName) {
        Patient patient = patientRepository.findByNameIgnoreCase(patientName).orElse(new Patient());
        return  patient.getAppointments();
    }
 

}
