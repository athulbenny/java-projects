package com.credai.securityapi.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
	public class Role {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long roleId;

	    @Column(name = "role")
	    private String role;

	    @Column(name = "members")
	    private List<String> userSet;

}
