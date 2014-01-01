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
import net.subclient.subsonic.responses.GetBookmarksResponse;
import net.subclient.subsonic.responses.SubsonicResponse;

/**
 * Wraps Subsonic API bookmarks handling methods
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface BookmarksAPI {
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 * @throws CompatibilityException
	 */
	public GetBookmarksResponse getBookmarks() throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException;
	
	/**
	 * 
	 * @param mediaId
	 * @param position
	 * @return
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 * @throws CompatibilityException
	 */
	public SubsonicResponse createBookmark(String mediaId, long position) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException;
	
	/**
	 * 
	 * @param bookmarkId
	 * @return
	 * @throws IOException
	 * @throws SubsonicException
	 * @throws InvalidResponseException
	 * @throws HTTPException
	 * @throws CompatibilityException
	 */
	public SubsonicResponse deleteBookmark(String bookmarkId) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException;
	
}
