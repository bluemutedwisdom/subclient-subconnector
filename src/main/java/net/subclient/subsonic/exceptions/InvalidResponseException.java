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
 * Exception thrown whenever a Subsonic returns an invalid content type or a non parseable response
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class InvalidResponseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/** Content type returned by Susbsinic server */
	private String contentType;
	
	/**
	 * Constructs a new InvalidResponseException with the deffined content-type
	 * @param contentType Content-type returned by the server
	 */
	public InvalidResponseException(String contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * Returns this exception content-type
	 * @return This exception content type
	 */
	public String getContentType() {
		return this.contentType;
	}
	
}
