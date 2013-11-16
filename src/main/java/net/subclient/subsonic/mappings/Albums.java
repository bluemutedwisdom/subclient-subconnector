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
 * Java representation of the "albums" JSON Object.
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class Albums {
	
	/** Array of childs in the album object */
	@SerializedName("album")
	private List<ChildInfo> albumsArray;
	
	/**
	 * Constructs a new Albums mapping with an empty albums array
	 */
	public Albums() {
		this.albumsArray = new ArrayList<ChildInfo>();
	}
	
	/**
	 * Returns this albums array
	 * @return this albums array
	 */
	public List<ChildInfo> getAlbumsArray() {
		return this.albumsArray;
	}
	
	/**
	 * Sets this albums array
	 * @param albumsArray New albums array
	 * @return this
	 */
	public Albums setAlbumsArray(List<ChildInfo> albumsArray) {
		this.albumsArray = albumsArray;
		return this;
	}
	/**
	 * Adds a new album to this albums array
	 * @param album
	 * @return this
	 */
	public Albums addAlbum(ChildInfo album) {
		this.albumsArray.add(album);
		return this;
	}
	
}
