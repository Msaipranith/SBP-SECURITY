package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersPojo {
	private int id;

	private String username;
	private String password;
	// private List<Address> address;

}
