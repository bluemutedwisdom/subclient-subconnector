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

package net.subclient.subsonic.api;

import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.responses.GetLicenseResponse;

import com.google.gson.JsonSyntaxException;

/**
 * Wraps Subsonic API system methods
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface SystemAPI {
	
	/**
     * Checks connectivity with current Subsonic server.
     * @return True if Subsonic server is reached. False otherwise.
     */
	public boolean ping();
	
	/**
     * Gets detailed information about current server license
     * @return A {@link net.subclient.subsonic.responses.GetLicenseResponse GetLicenseResponse} object with the server license information
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetLicenseResponse getLicense() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
}
