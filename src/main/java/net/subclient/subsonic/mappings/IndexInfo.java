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
 
 Copyright 2012 - 2014 Alejandro Celaya Alastrué
 
 */

package net.subclient.subsonic.mappings;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Java representation of an index JSON object
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class IndexInfo {
	
	/** Initial letter of the artists in this IndexInfo */
	private String name;
	/** Artists array of this IndexInfo */
	@SerializedName("artist")
	private List<FolderInfo> artistsArray;
	
	/**
	 * Constructs a new IndexInfo object with default values:
	 * <pre>
	 * 		{
	 * 			name : "-",
	 * 			artistsArray : []
	 * 		}
	 * </pre>
	 */
	public IndexInfo() {
		this.name 			= " - ";
		this.artistsArray	= new ArrayList<FolderInfo>();
	}
	
	public String getName() {
		return this.name;
	}
	public List<FolderInfo> getArtistsArray() {
		return this.artistsArray;
	}
	
	public IndexInfo setName(String name) {
		this.name = name;
		return this;
	}
	public IndexInfo setArtistsArray(List<FolderInfo> artistsArray) {
		this.artistsArray = artistsArray;
		return this;
	}
	public IndexInfo addArtist(FolderInfo artist) {
		this.artistsArray.add(artist);
		return this;
	}
	
	/**
	 * The string representation of this index is its name
	 * @return The string representation of this index
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
}
