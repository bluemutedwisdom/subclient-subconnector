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
import java.util.List;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.responses.GetPlaylistResponse;
import net.subclient.subsonic.responses.GetPlaylistsResponse;
import net.subclient.subsonic.responses.SubsonicResponse;

import com.google.gson.JsonSyntaxException;

/**
 * Wraps Subsonic playlist handling methods
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface PlaylistsAPI {

	/**
     * Gets the playlists available for the current user
     * @return A {@link net.subclient.subsonic.responses.GetPlaylistsResponse GetPlaylistsResponse} object with the playlists available
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetPlaylistsResponse getPlaylists() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	/**
     * Gets information about a specific playlist including songs list on that playlist
     * @param playlistId ID of the playlist to be returned
     * @return A {@link net.subclient.subsonic.responses.GetPlaylistResponse GetPlaylistResponse} object with information about the specified playlist
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetPlaylistResponse getPlaylist(String playlistId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
     * Creates a new playlist with the specified songs list and name
     * @param songsList List of IDs of songs that will form the playlist
     * @param name Name to be asigned to the playlist
     * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public SubsonicResponse createPlaylist(List<String> songsList, String name) 
	    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	 
	/**
     * Deletes the specified playlist
     * @param playlistId ID of the playlist to be deleted
     * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public SubsonicResponse deletePlaylist(String playlistId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
}
