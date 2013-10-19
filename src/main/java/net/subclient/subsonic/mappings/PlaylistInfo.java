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
 * Java representation of a playlist JSON object
 * @author Alejandro Celaya Alastrué
 */
public class PlaylistInfo extends FolderInfo implements Cloneable {
	
	/** Duration of this PlaylistInfo */
	private int duration;
	/** Number of songs of this PlaylistInfo */
	private int songCount;
	/** Date this PlaylistInfo was created */
	private String created;
	/** User owner of this PlaylistInfo */
	private String owner;
	/** Comment of this PlaylistInfo */
	private String comment;
	/** Defines if this PlaylistInfo is public */
	@SerializedName("public")
	private boolean isPublic;
	/** Songs array of this PlaylistInfo */
	@SerializedName("entry")
	private List<ChildInfo> entriesArray;
	
	/**
	 * Constructs a new PlaylistInfo object with default values
	 * <pre>
	 * 		{
	 * 			id : "-1",
	 * 			duration : 0,
	 * 			songCount : 0,
	 * 			created : "",
	 * 			name : "-",
	 * 			owner : "",
	 * 			comment : "",
	 * 			isPublic : false,
	 * 			entriesArray : []
	 * 		}
	 * </pre>
	 */
	public PlaylistInfo() {
		super();
		
		this.duration 		= 0;
		this.songCount		= 0;
		this.created 		= "";
		this.owner 			= "";
		this.comment		= "";
		this.isPublic 		= false;
		this.entriesArray	= new ArrayList<ChildInfo>();
	}
	
	public int getDuration() {
		return this.duration;
	}
	public int getSongCount() {
		return this.songCount;
	}
	public String getCreationDate() {
		return this.created;
	}
	public String getOwner() {
		return this.owner;
	}
	public String getComment() {
		return this.comment;
	}
	public boolean isPublic() {
		return this.isPublic;
	}
	public List<ChildInfo> getEntriesArray() {
		return this.entriesArray;
	}
	
	public PlaylistInfo setDuration(int duration) {
		this.duration = duration;
		return this;
	}
	public PlaylistInfo setSongCount(int songCount) {
		this.songCount = songCount;
		return this;
	}
	public PlaylistInfo setCreationDate(String created) {
		this.created = created;
		return this;
	}
	public PlaylistInfo setOwner(String owner) {
		this.owner = owner;
		return this;
	}
	public PlaylistInfo setComment(String comment) {
		this.comment = comment;
		return this;
	}
	public PlaylistInfo setPublic(boolean isPublic) {
		this.isPublic = isPublic;
		return this;
	}
	public PlaylistInfo setEntriesArray(List<ChildInfo> entriesArray) {
		this.entriesArray = entriesArray;
		return this;
	}
	public PlaylistInfo addEntry(ChildInfo entry) {
		this.entriesArray.add(entry);
		return this;
	}
	
	/**
	 * The string representation of this playlist is its title
	 * @return The string representation of this playlist 
	 */
	@Override
	public String toString() {
		return this.name;
	}
	@Override
	public PlaylistInfo clone() {
		PlaylistInfo info = new PlaylistInfo();
		info.setComment(this.getComment())
			.setCreationDate(this.getCreationDate())
			.setDuration(this.getDuration())
			.setEntriesArray(this.getEntriesArray())
			.setOwner(this.getOwner())
			.setPublic(this.isPublic())
			.setSongCount(this.getSongCount())
			.setId(this.getId())
			.setName(this.getName());
		return info;
	}
	
}
