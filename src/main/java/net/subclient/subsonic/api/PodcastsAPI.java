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
import java.net.URL;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.responses.GetPodcastResponse;
import net.subclient.subsonic.responses.GetPodcastsResponse;
import net.subclient.subsonic.responses.SubsonicResponse;

/**
 * Wraps Subsonic API podcast handling methods
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface PodcastsAPI {

	/**
     * Gets the list of podcasts in the server
     * @return A {@link net.subclient.subsonic.responses.GetPodcastsResponse GetPodcastsResponse} object with the list of channels the server is subscribed
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     */
	public GetPodcastsResponse getPodcasts() throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
     * Gets the episodes of the specified podcast
     * @param podcastId ID of the podcast channel whose episodes have to be returned
     * @return A {@link net.subclient.subsonic.responses.GetPodcastResponse GetPodcastResponse} object with the list of episodes of the podcast
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     */
	public GetPodcastResponse getPodcastEpisodes(String podcastId) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * Forces the server to check for new podcast episodes in all the channels
	 * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
	 * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
	 */
	public SubsonicResponse refreshPodcasts() throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * Creates a new podcast channel defined by a URL
	 * @param url The URL of the podcast as string
	 * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
	 * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
	 */
	public SubsonicResponse createPodcastChannel(String url) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * Creates a new podcast channel defined by a URL
	 * @param url The URL of the podcast as java.net.URL object
	 * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
	 * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
	 */
	public SubsonicResponse createPodcastChannel(URL url) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * Deletes the podcast channel defined by its ID
	 * @param channelId The ID of the podcast channel
	 * @return A {@link net.subclient.subsonic.responses.SubsonicResponse SubsonicResponse} object
	 * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
	 */
	public SubsonicResponse deletePodcastChannel(String channelId) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
}
