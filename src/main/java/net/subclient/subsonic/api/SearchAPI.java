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

import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.responses.SearchResponse;

import com.google.gson.JsonSyntaxException;

/**
 * Wraps Subsonic API search methods
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface SearchAPI {

	/**
     * Gets a list of max 20 artists, albums and songs that meet the specified query.
     * Corresponds to search2 Subsonic API method since search is deprecated
     * @param query Search criterion
     * @return A {@link net.subclient.subsonic.responses.SearchResponse SearchResponse} object with the artists, albums and songs
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public SearchResponse search(String query) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets the specified number of artists, albums and songs that meet the specified query.
     * Corresponds to search2 Subsonic API method since search is deprecated
     * @param query Search criterion
     * @param count Max number of artists, albums and songs to return
     * @return A {@link net.subclient.subsonic.responses.SearchResponse SearchResponse} object with the artists, albums and songs
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public SearchResponse search(String query, int count) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets the specified number of artists, albums and songs that meet the specified query.
     * Corresponds to search2 Subsonic API method since search is deprecated
     * @param query Search criterion
     * @param count Max number of artists, albums and songs to return
     * @param offset Index to start returning results. For pagination purposes
     * @return A {@link net.subclient.subsonic.responses.SearchResponse SearchResponse} object with the artists, albums and songs
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public SearchResponse search(String query, int count, int offset) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
}
