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

/**
 * Java representation of a folder JSON object
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class FolderInfo implements Cloneable {
	
	/** ID of this folder */
	private String id;
	/** Name of this folder */
	private String name;
	
	/**
	 * Constructs a new FolderInfo object with default values:
	 * <pre>
	 * 		{
	 * 			id : "-1",
	 * 			name : "-"
	 * 		}
	 * </pre>
	 */
	public FolderInfo() {
		this.id 	= "-1";
		this.name	= " - ";
	}
	
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	
	public FolderInfo setId(String id) {
		this.id = id;
		return this;
	}
	public FolderInfo setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * The string representation of this folder is its name
	 * @return The string representation of this folder
	 */
	@Override
	public String toString() {
		return this.name;
	}
	@Override
	public FolderInfo clone() {
		FolderInfo info = new FolderInfo();
		info.setId(this.getId())
			.setName(this.getName());
		return info;
	}
	
}
