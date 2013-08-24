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
import java.util.ArrayList;

import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.util.GetAlbumsType;
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

import com.google.gson.JsonSyntaxException;

/**
 * Connection interface defining all the methods needed in the Subsonic connection
 * @author Alejandro Celaya Alastrué
 */
public interface Connection {
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean ping();
	
	/**
	 * 
	 * @return GetLicenseResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public GetLicenseResponse getLicense() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
	 * 
	 * @return GetMusicFoldersResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public GetMusicFoldersResponse getMusicFolders() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
	 * 
	 * @return GetIndexesResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public GetIndexesResponse getIndexes() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	/**
	 * 
	 * @param musicFolderId
	 * @return GetIndexesResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public GetIndexesResponse getIndexes(String musicFolderId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	/**
	 * 
	 * @param modifiedSince
	 * @return GetIndexesResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public GetIndexesResponse getIndexes(long modifiedSince) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
	 * 
	 * @param uniqueFolderId
	 * @return GetMusicDirectoryResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public GetMusicDirectoryResponse getMusicDirectory(String uniqueFolderId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
	 * 
	 * @param query
	 * @return SearchResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public SearchResponse search(String query) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
	 * 
	 * @param query
	 * @param count
	 * @return SearchResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public SearchResponse search(String query, int count) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
	 * 
	 * @param query
	 * @param count
	 * @param offset
	 * @return SearchResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public SearchResponse search(String query, int count, int offset) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * 
	 * @return GetPlaylistsResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public GetPlaylistsResponse getPlaylists() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
	 * 
	 * @param playlistId
	 * @return GetPlaylistResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public GetPlaylistResponse getPlaylist(String playlistId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException;
	
	/**
	 * 
	 * @param songsList
	 * @param name
	 * @return SubsonicResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public SubsonicResponse createPlaylist(ArrayList<String> songsList, String name) 
	    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	 
	/**
	 * 
	 * @param playlistId
	 * @return SubsonicResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public SubsonicResponse deletePlaylist(String playlistId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * 
	 * @param type
	 * @return GetAlbumsResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws IllegalArgumentException
	 * @throws HTTPException
	 */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
	 * 
	 * @param type
	 * @param size
	 * @return GetAlbumsResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws IllegalArgumentException
	 * @throws HTTPException
	 */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
	 * 
	 * @param type
	 * @param size
	 * @param offset
	 * @return GetAlbumsResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws IllegalArgumentException
	 * @throws HTTPException
	 */
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size, int offset) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * 
	 * @return GetRandomSongsResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public GetRandomSongsResponse getRandomSongs() 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
	 * 
	 * @param folderId
	 * @return GetRandomSongsResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public GetRandomSongsResponse getRandomSongs(String folderId) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	/**
	 * 
	 * @param folderId
	 * @param size
	 * @return GetRandomSongsResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public GetRandomSongsResponse getRandomSongs(String folderId, int size) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * 
	 * @return GetPodcastsResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public GetPodcastsResponse getPodcasts() 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * 
	 * @param podcastId
	 * @return GetPodcastResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public GetPodcastResponse getPodcastEpisodes(String podcastId) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * 
	 * @param rating
	 * @return SubsonicResponse
	 * @throws JsonSyntaxException
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws CompatibilityException
	 * @throws HTTPException
	 */
	public SubsonicResponse setRating(AlbumRating rating) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException;
	
	/**
	 * 
	 * @param uniqueId
	 * @return InputStream
	 * @throws HTTPException
	 * @throws IOException
	 * @throws InvalidResponseException
	 */
	public InputStream download(String uniqueId) throws HTTPException, IOException, InvalidResponseException;
	
	/**
	 * 
	 * @param uniqueId
	 * @return InputStream
	 * @throws HTTPException
	 * @throws IOException
	 * @throws InvalidResponseException
	 */
	public InputStream stream(String uniqueId) throws HTTPException, IOException, InvalidResponseException;
	/**
	 * 
	 * @param uniqueId
	 * @param maxBitRate
	 * @return InputStream
	 * @throws IOException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public InputStream stream(String uniqueId, int maxBitRate) throws IOException, InvalidResponseException, HTTPException;
	
	/**
	 * 
	 * @param uniqueId
	 * @return String
	 */
	public String getStreamURL(String uniqueId);
	/**
	 * 
	 * @param uniqueId
	 * @param maxBitRate
	 * @return String
	 */
	public String getStreamURL(String uniqueId, int maxBitRate);
	
	/**
	 * 
	 * @param coverId
	 * @return BufferedImage
	 * @throws IOException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public BufferedImage getCoverArt(String coverId) throws IOException, InvalidResponseException, HTTPException;
	/**
	 * 
	 * @param coverArt
	 * @param size
	 * @return BufferedImage
	 * @throws IOException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 */
	public BufferedImage getCoverArt(String coverArt, int size) throws IOException, InvalidResponseException, HTTPException;
	
}
