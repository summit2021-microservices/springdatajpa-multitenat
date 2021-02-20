package com.jornadacolaborativa.microservice.exception;

public class TenantException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -5588328248090476564L;

	public TenantException(String message) {
		super(message);			}


	public TenantException(String message, Throwable cause) {
		super(message, cause);
	}
}