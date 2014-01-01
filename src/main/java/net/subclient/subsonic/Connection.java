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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.api.BrowseAPI;
import net.subclient.subsonic.api.MediaListAPI;
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
public interface Connection extends SystemAPI, BrowseAPI, MediaListAPI, SearchAPI, PlaylistsAPI, PodcastsAPI {
	
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
	
	/**
     * Gets a stream of an element (directory or song) to be downloaded.
     * @param uniqueId ID of the element to be downloaded
     * @return InputStream to the content to be downloaded
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws IOException 
     * @throws HTTPException If the server response code is other than 200
     */
	public InputStream download(String uniqueId) throws HTTPException, IOException, InvalidResponseException;
	
	/**
     * Gets the stream of a song to be played.
     * @param uniqueId ID of the song to be played
     * @return InputStream of the song to be played
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws IOException 
     * @throws HTTPException If the server response code is other than 200
     */
	public InputStream stream(String uniqueId) throws HTTPException, IOException, InvalidResponseException;
	/**
     * Gets the stream of a song to be played.
     * @param uniqueId ID of the song to be played
     * @param maxBitRate Max bitrate of the stream
     * @return InputStream of the song to be played
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200
     */
	public InputStream stream(String uniqueId, int maxBitRate) throws IOException, InvalidResponseException, HTTPException;
	
	/**
     * Returns a valid URL for streaming a song from the current Subsonic server specified by its ID
     * @param uniqueId ID of the song to be played
     * @return streaming URL
     */
	public String getStreamURL(String uniqueId);
	/**
     * Returns a valid URL for streaming a song from the current Subsonic server specified by its ID
     * @param uniqueId ID of the song to be played
     * @param maxBitRate Max bitrate of the stream
     * @return streaming URL
     */
	public String getStreamURL(String uniqueId, int maxBitRate);
	
	/**
     * Returns a cover art specified by the provided ID and a size of 100x100 pixels
     * @param coverId ID of the cover art to be returned
     * @return BufferedImage with the cover art
     * @throws IOException
     * @throws InvalidResponseException If Subsonic servers returns a non parseable response
     * @throws HTTPException If the server response code is other than 200
     */
	public BufferedImage getCoverArt(String coverId) throws IOException, InvalidResponseException, HTTPException;
	/**
     * Returns a cover art defined by the provided ID and a specified size
     * @param coverArt ID of the cover art to be returned
     * @param size Size in pixels of the returned image
     * @return BufferedImage with the cover art
     * @throws IOException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200
     */
	public BufferedImage getCoverArt(String coverArt, int size) throws IOException, InvalidResponseException, HTTPException;
	
}
