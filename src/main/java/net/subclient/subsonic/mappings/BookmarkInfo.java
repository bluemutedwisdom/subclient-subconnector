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

/**
 * Representes the bookmark object returned by Subsonic API while calling {@link net.subclient.subsonic.Connection#getBookmarks getBookmarks} method
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class BookmarkInfo {

	private float position;
	private String username;
	private String created;
	private String changed;
	private ChildInfo entry;
	
	public BookmarkInfo() {
		this.position 	= 0;
		this.username	= "";
		this.created 	= "";
		this.changed 	= "";
		this.entry 		= new ChildInfo();
	}

	public float getPosition() {
		return position;
	}
	public BookmarkInfo setPosition(float position) {
		this.position = position;
		return this;
	}

	public String getUsername() {
		return username;
	}
	public BookmarkInfo setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getCreated() {
		return created;
	}
	public BookmarkInfo setCreated(String created) {
		this.created = created;
		return this;
	}

	public String getChanged() {
		return changed;
	}
	public BookmarkInfo setChanged(String changed) {
		this.changed = changed;
		return this;
	}

	public ChildInfo getEntry() {
		return entry;
	}
	public BookmarkInfo setEntry(ChildInfo entry) {
		this.entry = entry;
		return this;
	}
	
}
