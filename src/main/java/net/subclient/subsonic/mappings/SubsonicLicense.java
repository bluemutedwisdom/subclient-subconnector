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

package net.subclient.subsonic.mappings;

import com.google.gson.annotations.SerializedName;

/**
 * Java representation of the Subsonic license JSON Object
 * @author Alejandro Celaya Alastrué
 */
public class SubsonicLicense {
	
	/** Defines if this SubsonicLicense is valid */
	@SerializedName("valid")
	private boolean isValid;
	/** Email of this SubsonicLicense */
	private String email;
	/** Date of this SubsonicLicense */
	private String date;
	/** Key of this SubsonicLicense */
	private String key;
	
	/**
	 * Constructs a new SubsonicLicense object with defauklt values:
	 * <pre>
	 * 		{
	 * 			isValid : false,
	 * 			email : "john.doe@domain.com",
	 * 			date : "",
	 * 			key : ""
	 * 		}
	 * </pre>
	 */
	public SubsonicLicense() {
		this.isValid	= false;
		this.email 		= "john.doe@domain.com";
		this.date		= "";
		this.key		= "";
	}
	
	public boolean isValid() {
		return this.isValid;
	}
	public String getEmail() {
		return this.email;
	}
	public String getDate() {
		return this.date;
	}
	public String getKey() {
		return this.key;
	}
	
	public SubsonicLicense setIsValid(boolean isValid) {
		this.isValid = isValid;
		return this;
	}
	public SubsonicLicense setEmail(String email) {
		this.email = email;
		return this;
	}
	public SubsonicLicense setDate(String date) {
		this.date = date;
		return this;
	}
	public SubsonicLicense setKey(String key) {
		this.key = key;
		return this;
	}
	
	
}
