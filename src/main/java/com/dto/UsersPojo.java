package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) for Users.
 * Used for transferring user data between the controller layer and the client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersPojo {

	private int id;
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String role;
	// private List<Address> address;

}
