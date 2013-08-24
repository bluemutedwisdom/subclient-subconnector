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

package net.subclient.subsonic.responses;

import net.subclient.subsonic.mappings.Albums;

/**
 * Object returned on {@link net.subclient.subsonic.SubsonicConnection#getAlbumsList(net.subclient.subsonic.util.GetAlbumsType) getAlbumsList} calls
 * @author Alejandro Celaya Alastrué
 * @see net.subclient.subsonic.SubsonicConnection
 */
public class GetAlbumsResponse extends SubsonicResponse {
	
	/** Albums list wrapper object */
	private Albums albumList;
	
	/**
	 * Constructs a new GetAlbumsResponse with a default constructed Albums object
	 */
	public GetAlbumsResponse() {
		super();
		this.albumList = new Albums();
	}
	
	public Albums getAlbums() {
		return albumList;
	}
	public GetAlbumsResponse setAlbums(Albums albums) {
		this.albumList = albums;
		return this;
	}
	
}
