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
 
 Copyright 2012 - 2014 Alejandro Celaya Alastrué
 
 */

package net.subclient.subsonic.responses;

import net.subclient.subsonic.util.Version;

/**
 * Subsonic base class for the Subsonic API call responses
 * @see net.subclient.subsonic.Connection
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class SubsonicResponse implements Response {
	
	/** Status returned on any successfull response */
	public static final String STATUS_OK 		= "ok";
	/** Status returned on any failed response */
	public static final String STATUS_FAILED	= "failed";
	
	/** Status of this response */
	private String status;
	/** Version of the server returning this version */
	private String version;
	
	/**
	 * Constructs a new SubsonicResponse with status {@link #STATUS_OK OK} and version 1.0.0
	 */
	public SubsonicResponse() {
		this.status		= STATUS_OK;
		this.version	= "1.0.0";
	}
	
	@Override
	public String getStatus() {
		return this.status;
	}
	@Override
	public Version getVersion() {
		try {			
			return Version.parseVersion(this.version);
		} catch(IllegalArgumentException e) {
			return new Version(1);
		}
	}
	
	@Override
	public SubsonicResponse setStatus(String status) {
		this.status = status;
		return this;
	}
	@Override
	public SubsonicResponse setVersion(String version) {
		this.version = version;
		return this;
	}
	@Override
	public SubsonicResponse setVersion(Version version) {
		this.version = version.toString(true);
		return this;
	}
	
}
