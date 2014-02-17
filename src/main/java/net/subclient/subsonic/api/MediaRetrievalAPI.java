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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;

/**
 * Wraps Subsonic API media retrieval methods
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface MediaRetrievalAPI {

	/**
     * Gets a stream of an element (directory or song) to be downloaded.
     * @param uniqueId ID of the element to be downloaded
     * @return InputStream to the content to be downloaded
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws IOException 
     * @throws HTTPException If the server response code is other than 200
     * @throws CompatibilityException If this method is not compatible with the current server
     */
	public InputStream download(String uniqueId) throws HTTPException, IOException, InvalidResponseException, CompatibilityException;
	
	/**
     * Gets the stream of a song to be played.
     * @param uniqueId ID of the song to be played
     * @return InputStream of the song to be played
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws IOException 
     * @throws HTTPException If the server response code is other than 200
     * @throws CompatibilityException If this method is not compatible with the current server
     */
	public InputStream stream(String uniqueId) throws HTTPException, IOException, InvalidResponseException, CompatibilityException;
	/**
     * Gets the stream of a song to be played.
     * @param uniqueId ID of the song to be played
     * @param maxBitRate Max bitrate of the stream
     * @return InputStream of the song to be played
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200
     * @throws CompatibilityException If this method is not compatible with the current server
     */
	public InputStream stream(String uniqueId, int maxBitRate) throws IOException, InvalidResponseException, HTTPException, CompatibilityException;
	
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
     * @throws CompatibilityException If this method is not compatible with the current server
     */
	public BufferedImage getCoverArt(String coverId) throws IOException, InvalidResponseException, HTTPException, CompatibilityException;
	/**
     * Returns a cover art defined by the provided ID and a specified size
     * @param coverArt ID of the cover art to be returned
     * @param size Size in pixels of the returned image
     * @return BufferedImage with the cover art
     * @throws IOException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200
     * @throws CompatibilityException If this method is not compatible with the current server
     */
	public BufferedImage getCoverArt(String coverArt, int size) throws IOException, InvalidResponseException, HTTPException, CompatibilityException;
	
}
