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

import net.subclient.subsonic.mappings.MusicFolders;

/**
 * Object returned on {@link net.subclient.subsonic.SubsonicConnection#getMusicFolders() getMusicFolders} calls
 * @author Alejandro Celaya Alastrué
 * @see net.subclient.subsonic.SubsonicConnection
 */
public class GetMusicFoldersResponse extends SubsonicResponse {
	
	/** MusicFolders wrapper object */
	private MusicFolders musicFolders;
	
	/**
	 * Constructs a new GetMusicFoldersResponse with a default constructed MusicFolders object
	 */
	public GetMusicFoldersResponse() {
		super();
		this.musicFolders = new MusicFolders();
	}
	
	public MusicFolders getMusicFolders() {
		return this.musicFolders;
	}
	public GetMusicFoldersResponse setMusicFolders(MusicFolders musicFolders) {
		this.musicFolders = musicFolders;
		return this;
	}
	
}
