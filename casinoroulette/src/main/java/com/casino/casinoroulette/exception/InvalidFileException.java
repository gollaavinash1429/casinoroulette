package com.casino.casinoroulette.exception;

public class InvalidFileException extends Exception {

	public InvalidFileException(String message) {
		super(message);
	}

	public InvalidFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
