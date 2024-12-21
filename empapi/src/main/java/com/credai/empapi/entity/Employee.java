package com.credai.empapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="EMPLOYEE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long empId;// in database emp_id: _i => I
	
	@NotNull(message="name cannot be null")
	@NotEmpty(message="name cannot be empty")
	@Size(min=3,message="atleast 4 charcters")
	@Column(name="name", length= 255)
	private String name;
	
	@Positive
	@Column(name="age")
	private int age;
	
	@PositiveOrZero(message="salary should be a positive value or zero")
	@NotNull(message="salary cannot be null")
	@Column(name="salary")
	private double salary;
	
	@Column(name="address", length =255)
	private String address;
}
