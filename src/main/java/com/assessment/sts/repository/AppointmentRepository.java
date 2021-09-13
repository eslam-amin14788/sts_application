package com.assessment.sts.repository;

import com.assessment.sts.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    public List<Appointment> findByReservationDate(LocalDate date);
    
    public List<Appointment> findAllByReservationDateBetween(LocalDateTime from,LocalDateTime to);


}
