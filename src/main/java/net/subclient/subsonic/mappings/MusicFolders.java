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
 * Java representation of a musicFolders JSON Object
 * @author Alejandro Celaya Alastrué
 */
public class MusicFolders {
	
	/** Array of folders in this MusicFolder */
	@SerializedName("musicFolder")
	private List<FolderInfo> musicFoldersArray;
	
	/**
	 * Constructs a new MusicFolders object with default values
	 * <pre>
	 * 		{
	 * 			musicFoldersArray : []
	 * 		}
	 * </pre>
	 */
	public MusicFolders() {
		this.musicFoldersArray = new ArrayList<FolderInfo>();
	}
	
	public List<FolderInfo> getMusicFoldersArray() {
		return this.musicFoldersArray;
	}
	
	public MusicFolders setMusicFoldersArray(List<FolderInfo> musicFoldersArray) {
		this.musicFoldersArray = musicFoldersArray;
		return this;
	}
	public MusicFolders addMusicFolder(FolderInfo musicFolder) {
		this.musicFoldersArray.add(musicFolder);
		return this;
	}
	
}
