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
import java.util.List;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.responses.GetAlbumsResponse;
import net.subclient.subsonic.responses.GetIndexesResponse;
import net.subclient.subsonic.responses.GetLicenseResponse;
import net.subclient.subsonic.responses.GetMusicDirectoryResponse;
import net.subclient.subsonic.responses.GetMusicFoldersResponse;
import net.subclient.subsonic.responses.GetPlaylistResponse;
import net.subclient.subsonic.responses.GetPlaylistsResponse;
import net.subclient.subsonic.responses.GetPodcastResponse;
import net.subclient.subsonic.responses.GetPodcastsResponse;
import net.subclient.subsonic.responses.GetRandomSongsResponse;
import net.subclient.subsonic.responses.SearchResponse;
import net.subclient.subsonic.responses.SubsonicResponse;
import net.subclient.subsonic.util.AlbumRating;
import net.subclient.subsonic.util.GetAlbumsType;

import com.google.gson.JsonSyntaxException;

/**
 * Connection interface defining all the methods needed in the Subsonic connection
 * @author Alejandro Celaya Alastrué
 */
public interface Connection {
	
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
	
	/**
     * Gets the available music folders list
     * @return A {@link net.subclient.subsonic.responses.GetMusicFoldersResponse GetMusicFoldersResponse} object with the folders list
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetMusicFoldersResponse getMusicFolders() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
     * Gets a list of all the directories (indexes) in the root of all music folders
     * @return A {@link net.subclient.subsonic.responses.GetIndexesResponse GetIndexesResponse} object with the indexes list
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetIndexesResponse getIndexes() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	/**
     * Gets a list of all the directories (indexes) in the root of the specified music folder
     * @param musicFolderId Id of the music folder to be listed
     * @return A {@link net.subclient.subsonic.responses.GetIndexesResponse GetIndexesResponse} object with the indexes list
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetIndexesResponse getIndexes(String musicFolderId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	/**
     * Gets a list of all the directories (indexes) in the root of all music folders that has been modified after the specified timestamp
     * @param modifiedSince Timestamp in milliseconds
     * @return A {@link net.subclient.subsonic.responses.GetIndexesResponse GetIndexesResponse} object with the indexes list
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetIndexesResponse getIndexes(long modifiedSince) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	/**
     * Gets a list of all the directories (indexes) in the root of the specified music folder that has been modified after the specified timestamp
     * @param musicFolderId Id of the music folder to be listed
     * @param modifiedSince Timestamp in milliseconds
     * @return A {@link net.subclient.subsonic.responses.GetIndexesResponse GetIndexesResponse} object with the indexes list
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetIndexesResponse getIndexes(String musicFolderId, long modifiedSince) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
     * Returns all the albums and songs in specified directory
     * @param uniqueFolderId ID of the directory to be listed
     * @return A {@link net.subclient.subsonic.responses.GetMusicDirectoryResponse GetMusicDirectoryResponse} object with the albums and songs
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetMusicDirectoryResponse getMusicDirectory(String uniqueFolderId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
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
	
	/**
     * Gets a list of 10 albums of the specified type
     * @param type Type of the albums to be returned. Valid types are defined in {@link net.subclient.subsonic.util.GetAlbumsType GetAlbumsType}
     * @return A {@link net.subclient.subsonic.responses.GetAlbumsResponse GetAlbumsResponse} object with the list of albums
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     * @see net.subclient.subsonic.util.GetAlbumsType
     */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets the defined number of albums of the specified type
     * @param type Type of the albums to be returned. Valid types are defined in {@link net.subclient.subsonic.util.GetAlbumsType GetAlbumsType}
     * @param size Number of albums to return
     * @return A {@link net.subclient.subsonic.responses.GetAlbumsResponse GetAlbumsResponse} object with the list of albums
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets the defined number of albums of the specified type
     * @param type Type of the albums to be returned. Valid types are defined in {@link net.subclient.subsonic.util.GetAlbumsType GetAlbumsType}
     * @param size Number of albums to return
     * @param offset Index to start returning results. For pagination purposes
     * @return A {@link net.subclient.subsonic.responses.GetAlbumsResponse GetAlbumsResponse} object with the list of albums
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200 
     */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size, int offset) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
     * Gets a list of 10 random songs in all music folders
     * @return A {@link net.subclient.subsonic.responses.GetRandomSongsResponse GetRandomSongsResponse} object with the list of songs
     * @throws SubsonicException If a Subsonic error occurs
     * @throws JsonSyntaxException
     * @throws IOException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response
     * @throws CompatibilityException If this method is not compatible with the current server
     * @throws HTTPException If the server response code is other than 200
     */
	public GetRandomSongsResponse getRandomSongs() 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets a list of 10 random songs in a specific music folder
     * @param folderId ID of the music folder used to get the songs list
     * @return A {@link net.subclient.subsonic.responses.GetRandomSongsResponse GetRandomSongsResponse} object with the list of songs
     * @throws JsonSyntaxException
     * @throws IOException
     * @throws SubsonicException If a Subsonic error occurs
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     */
	public GetRandomSongsResponse getRandomSongs(String folderId) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
     * Gets a defined number of random songs in a specific music folder
     * @param folderId ID of the music folder used to get the songs list
     * @param size Number of songs the be returned
     * @return A {@link net.subclient.subsonic.responses.GetRandomSongsResponse GetRandomSongsResponse} object with the list of songs
     * @throws JsonSyntaxException
     * @throws IOException
     * @throws SubsonicException If a Subsonic error occurs
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     */
	public GetRandomSongsResponse getRandomSongs(String folderId, int size) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
     * Gets the list of podcasts in the server
     * @return A {@link net.subclient.subsonic.responses.GetPodcastsResponse GetPodcastsResponse} object with the list of channels the server is subscribed
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     */
	public GetPodcastsResponse getPodcasts() 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
     * Gets the episodes of the specified podcast
     * @param podcastId ID of the podcast channel whose episodes have to be returned
     * @return A {@link net.subclient.subsonic.responses.GetPodcastResponse GetPodcastResponse} object with the list of episodes of the podcast
     * @throws SubsonicException If a Subsonic error occurs
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws CompatibilityException If this method is not compatible with the current server 
     * @throws HTTPException If the server response code is other than 200
     */
	public GetPodcastResponse getPodcastEpisodes(String podcastId) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
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
