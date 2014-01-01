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

import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.responses.GetIndexesResponse;
import net.subclient.subsonic.responses.GetMusicDirectoryResponse;
import net.subclient.subsonic.responses.GetMusicFoldersResponse;

import com.google.gson.JsonSyntaxException;

/**
 * Wraps Subsonic API browsing methods
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface BrowseAPI {

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
	
}
