package com.decathlon.Batch;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // fait getter et setters
@Entity
@NoArgsConstructor
@Table(name = "booking")
public class BookingModel {

	@Id
	@GeneratedValue
	private Long id;
	

	private LocalDateTime endDate;

	
}
