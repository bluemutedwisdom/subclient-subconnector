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

package net.subclient.subsonic.responses;

import net.subclient.subsonic.mappings.SubsonicLicense;

/**
 * Object returned on {@link net.subclient.subsonic.Connection#getLicense() getLicense} calls
 * @see net.subclient.subsonic.Connection
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class GetLicenseResponse extends SubsonicResponse {
	
	/** SubsonicLicense wrapper object */
	private SubsonicLicense license;
	
	/**
	 * Constructs a new GetLicenseResponse with a default constructed SubsonicLicense
	 */
	public GetLicenseResponse() {
		super();
		this.license = new SubsonicLicense();
	}
	
	public SubsonicLicense getSubsonicLicense() {
		return this.license;
	}
	public GetLicenseResponse setSubsonicLicense(SubsonicLicense license) {
		this.license = license;
		return this;
	}
	
}
