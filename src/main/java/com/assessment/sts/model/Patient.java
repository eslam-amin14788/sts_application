package com.assessment.sts.model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Table(name = "patient")
@Entity
@Data
public class Patient implements Serializable{
	
    private static final long serialVersionUID=1L;  
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private LocalDate birthDate;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @Fetch(FetchMode.SUBSELECT) 
    @JsonIgnore
    private List<Appointment> appointments=new ArrayList<Appointment>();

}
