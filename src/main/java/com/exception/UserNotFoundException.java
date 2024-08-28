package com.exception;

public class UserNotFoundException extends RuntimeException{

	private String msg;
	public UserNotFoundException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		this.msg=string;
	
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
