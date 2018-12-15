package com.cg.pwa.exception;

@SuppressWarnings("serial")
public class InvalidAmount  extends Exception {
	public InvalidAmount() {
		super("*****Invalid Amount*****\n"+ "*****Amount can't be negative****\n"+ "****Amount can't be zero****");
}
}
