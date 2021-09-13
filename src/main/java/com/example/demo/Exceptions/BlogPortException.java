package com.example.demo.Exceptions;

	public class BlogPortException extends RuntimeException {
	    public BlogPortException(String exMessage, Exception exception) {
	        super(exMessage, exception);
	    }

	    public BlogPortException(String exMessage) {
	        super(exMessage);
	    }
	}
