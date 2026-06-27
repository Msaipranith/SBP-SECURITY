package com.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserNotFoundException extends RuntimeException {

	private String msg;

	public UserNotFoundException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		this.msg = string;

	}

}
