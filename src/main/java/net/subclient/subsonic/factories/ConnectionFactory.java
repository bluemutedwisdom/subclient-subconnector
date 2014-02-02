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

package net.subclient.subsonic.factories;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import net.subclient.subsonic.Connection;
import net.subclient.subsonic.SubsonicConnection;

/**
 * Includes static factory methods to create Connection objects
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class ConnectionFactory {
	
	private static final String DEFAULT_USERNAME 	= "admin"; 
	private static final String DEFAULT_PASWWORD	= ""; 
	
	/**
	 * Constructs a new Connection object by using defined URL and default Subsonic user ("admin") and password ("")
	 * @param url
	 * @return A Connection object
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws MalformedURLException
	 */
	public static Connection createConnection(String url) throws KeyManagementException, NoSuchAlgorithmException, MalformedURLException {
		return createConnection(url, DEFAULT_USERNAME, DEFAULT_PASWWORD);
	}
	
	/**
	 * Constructs a new Connection object by using defined URL and user and default password ("")
	 * @param url
	 * @param user
	 * @return A Connection object
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws MalformedURLException
	 */
	public static Connection createConnection(String url, String user) throws KeyManagementException, NoSuchAlgorithmException, MalformedURLException {
		return createConnection(url, user, DEFAULT_PASWWORD);
	}
	
	/**
	 * Constructs a new connection with provided data
	 * @param url
	 * @param user
	 * @param password
	 * @return A Connection object
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws MalformedURLException
	 */
	public static Connection createConnection(String url, String user, String password) throws KeyManagementException, NoSuchAlgorithmException, MalformedURLException {
		return new SubsonicConnection(new URL(url), user, password);
	}
	
}
