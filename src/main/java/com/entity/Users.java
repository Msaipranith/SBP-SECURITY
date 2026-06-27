package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity representing a User in the database.
 * Maps to the 'users' table.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String role;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id")
//	private List<Address> address;

}
