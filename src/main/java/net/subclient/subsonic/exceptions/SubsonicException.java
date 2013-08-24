/*
 
 This file is part of Subconnector.
 
 Subconnector is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 Subconnector is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with Subconnector. If not, see <http://www.gnu.org/licenses/>.
 
 Copyright 2012, 2013 Alejandro Celaya Alastrué
 
 */

package net.subclient.subsonic.exceptions;

/**
 * Exception thrown whenever a Subsonic API call produces an error
 * @author Alejandro Celaya Alastrué
 */
public class SubsonicException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/** Generic Subsonic server communication error */
	public final static int GENERIC_ERROR          = 0;
	/** Some mandatory parameter is missing */
	public final static int MISSING_PARAMETER      = 10;
	/** Incompatible Subsonic REST protocol version. Client must upgrade. */
	public final static int CLIENT_MUST_UPGRADE    = 20;
	/** Incompatible Subsonic REST protocol version. Server must upgrade. */
	public final static int SERVER_MUST_UPGRADE    = 30;
	/** Wrong user or password */
	public final static int WRONG_USER_OR_PASS     = 40;
	/** Current user attempted to perform an action for which he has no permission */
	public final static int PERMISSION_DENIED      = 50;
	/** Server trial period has expired */
	public final static int TRIAL_PERIOD_OVER      = 60;
	/** Requested information not found */
	public final static int DATA_NOT_FOUND         = 70;
	
	/** Error code returned by Subsonic server */
	private int code;
	/** Message returned by Subsonic server */
	private String message;
	
	/**
	 * Constructs a new SubsonicException with the {@link #GENERIC_ERROR GENERIC_ERROR} code
	 */
	public SubsonicException() {
		this(GENERIC_ERROR, "");
	}
	/**
	 * Constructs a new SubsonicException with the specified code
	 * @param code Code of the produced exception
	 */
	public SubsonicException(int code) {
		this(code, "");
	}
	/**
	 * Constructs a new SubsonicException with the specified code and message
	 * @param code of the produced exception
	 * @param message Message of the produced exception
	 */
	public SubsonicException(int code, String message) {
		this.code 		= code;
		this.message	= message;
	}
	
	/**
	 * Returns this exception error code
	 * @return Error code
	 */
	public int getCode() {
		return this.code;
	}
	/**
	 * Returns this exception error message
	 * @return Error message
	 */
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
