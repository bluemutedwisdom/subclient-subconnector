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

package net.subclient.subsonic.mappings;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Java representation of the "playlists" JSON object
 * @author Alejandro Celaya Alastrué
 */
public class Playlists {
	
	/** Playlists array of this Playlists object */
	@SerializedName("playlist")
	private List<PlaylistInfo> playlistsArray;
	
	/**
	 * Constructs a new Playlists object with default values
	 * <pre>
	 * 		{
	 * 			playlistsArray : []
	 * 		}
	 * </pre>
	 */
	public Playlists() {
		this.playlistsArray = new ArrayList<PlaylistInfo>();
	}
	
	public List<PlaylistInfo> getPlaylistsArray() {
		return this.playlistsArray;
	}
	
	public Playlists setPlaylistsArray(List<PlaylistInfo> playlistsArray) {
		this.playlistsArray = playlistsArray;
		return this;
	}
	public Playlists addPlaylist(PlaylistInfo playlist) {
		this.playlistsArray.add(playlist);
		return this;
	}
	
}
