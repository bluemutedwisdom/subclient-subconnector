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

package net.subclient.subsonic;

import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.api.BrowseAPI;
import net.subclient.subsonic.api.MediaListAPI;
import net.subclient.subsonic.api.MediaRetrievalAPI;
import net.subclient.subsonic.api.PlaylistsAPI;
import net.subclient.subsonic.api.PodcastsAPI;
import net.subclient.subsonic.api.SearchAPI;
import net.subclient.subsonic.api.SystemAPI;
import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.responses.SubsonicResponse;
import net.subclient.subsonic.util.AlbumRating;

import com.google.gson.JsonSyntaxException;

/**
 * Connection interface defining all the methods needed in the Subsonic connection
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface Connection extends SystemAPI, BrowseAPI, MediaListAPI, SearchAPI, PlaylistsAPI, PodcastsAPI, MediaRetrievalAPI {
	
	/**
     * Sets an album rating
     * @param rating AlbumRating object with the album id and the rating to be set to that album
     * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     * @see net.subclient.subsonic.util.AlbumRating
     */
	public SubsonicResponse setRating(AlbumRating rating) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * Attaches a star to a song, album or artist
	 * @param id Album, artist or song's ID
	 * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
	 */
	public SubsonicResponse star(String id) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * Removes the star from a song, album or artist
	 * @param id Album, artist or song's ID
	 * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
	 */
	public SubsonicResponse unstar(String id) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
}
