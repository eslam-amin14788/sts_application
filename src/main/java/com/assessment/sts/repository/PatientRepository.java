package com.assessment.sts.repository;

import com.assessment.sts.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    public Optional<Patient> findByNameIgnoreCase(String name);
}
