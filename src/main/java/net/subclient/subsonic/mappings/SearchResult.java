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
 * Java representation of the "searchResult" JSON Object.
 * @author Alejandro Celaya Alastrué
 */
public class SearchResult {
	
	/** Songs of this SearchResult */
	@SerializedName("song")
	private List<ChildInfo> songsArray;
	/** Albums of this SearchResult */
	@SerializedName("album")
	private List<ChildInfo> albumsArray;
	/** Artists of this SearchResult */
	@SerializedName("artist")
	private List<FolderInfo> artistsArray;
	
	/**
	 * Sonstructs a new SearchResult object with default values:
	 * <pre>
	 * 		{
	 * 			songsArray : [],
	 * 			albumsArray : [],
	 * 			artistsArray : []
	 * 		}
	 * </pre>
	 */
	public SearchResult() {
		this.songsArray 	= new ArrayList<ChildInfo>();
		this.albumsArray 	= new ArrayList<ChildInfo>();
		this.artistsArray	= new ArrayList<FolderInfo>();
	}
	
	public List<ChildInfo> getSongsArray() {
		return this.songsArray;
	}
	public List<ChildInfo> getAlbumsArray() {
		return this.albumsArray;
	}
	public List<FolderInfo> getArtistsArray() {
		return this.artistsArray;
	}
	
	public SearchResult setSongsArray(List<ChildInfo> songsArray) {
		this.songsArray = songsArray;
		return this;
	}
	public SearchResult addSong(ChildInfo song) {
		this.songsArray.add(song);
		return this;
	}
	public SearchResult setAlbumsArray(List<ChildInfo> albumsArray) {
		this.albumsArray = albumsArray;
		return this;
	}
	public SearchResult addAlbum(ChildInfo album) {
		this.albumsArray.add(album);
		return this;
	}
	public SearchResult setArtistsArray(List<FolderInfo> artistsArray) {
		this.artistsArray = artistsArray;
		return this;
	}
	public SearchResult addArtist(FolderInfo artist) {
		this.artistsArray.add(artist);
		return this;
	}
	
}
