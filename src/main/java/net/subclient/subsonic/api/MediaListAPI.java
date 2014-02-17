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

package net.subclient.subsonic.api;

import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.responses.GetAlbumsResponse;
import net.subclient.subsonic.responses.GetRandomSongsResponse;
import net.subclient.subsonic.responses.GetStarredResponse;
import net.subclient.subsonic.util.GetAlbumsType;

/**
 * Wraps Subsonic API media listing methods
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface MediaListAPI {

	/**
     * Gets a list of 10 albums of the specified type
     * @param type Type of the albums to be returned. Valid types are defined in {@link net.subclient.subsonic.util.GetAlbumsType GetAlbumsType}
     * @return A {@link net.subclient.subsonic.responses.GetAlbumsResponse GetAlbumsResponse} object with the list of albums
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException  
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     * @see net.subclient.subsonic.util.GetAlbumsType
     */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets the defined number of albums of the specified type
     * @param type Type of the albums to be returned. Valid types are defined in {@link net.subclient.subsonic.util.GetAlbumsType GetAlbumsType}
     * @param size Number of albums to return
     * @return A {@link net.subclient.subsonic.responses.GetAlbumsResponse GetAlbumsResponse} object with the list of albums
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException  
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets the defined number of albums of the specified type
     * @param type Type of the albums to be returned. Valid types are defined in {@link net.subclient.subsonic.util.GetAlbumsType GetAlbumsType}
     * @param size Number of albums to return
     * @param offset Index to start returning results. For pagination purposes
     * @return A {@link net.subclient.subsonic.responses.GetAlbumsResponse GetAlbumsResponse} object with the list of albums
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size, int offset) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
     * Gets a list of 10 random songs in all music folders
     * @return A {@link net.subclient.subsonic.responses.GetRandomSongsResponse GetRandomSongsResponse} object with the list of songs
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response
     * @throws CompatibilityException If this method is not compatible with the current server
     * @throws HTTPException If the server response code is other than 200
     */
	public GetRandomSongsResponse getRandomSongs() throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets a list of 10 random songs in a specific music folder
     * @param folderId ID of the music folder used to get the songs list
     * @return A {@link net.subclient.subsonic.responses.GetRandomSongsResponse GetRandomSongsResponse} object with the list of songs
     * @throws IOException
     * @throws SubsonicException If a Subsonic error occurs
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     */
	public GetRandomSongsResponse getRandomSongs(String folderId) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets a defined number of random songs in a specific music folder
     * @param folderId ID of the music folder used to get the songs list
     * @param size Number of songs the be returned
     * @return A {@link net.subclient.subsonic.responses.GetRandomSongsResponse GetRandomSongsResponse} object with the list of songs
     * @throws IOException
     * @throws SubsonicException If a Subsonic error occurs
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     */
	public GetRandomSongsResponse getRandomSongs(String folderId, int size) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;

	/**
	 * Returns all artists, albums and songs that have received a star
	 * @return A {@link net.subclient.subsonic.responses.GetStarredResponse GetStarredResponse} object
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
	 */
	public GetStarredResponse getStarred() throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
}
