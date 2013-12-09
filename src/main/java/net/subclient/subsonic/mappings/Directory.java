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
 * Java representation of the "directory" JSON object
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class Directory {
	
	/** ID of this directory */
	private String id;
	/** Name of this directory */
	private String name;
	/** Array of childs of this directory */
	@SerializedName("child")
	private List<ChildInfo> childsArray;
	
	/**
	 * Constructs a new Directory object with default values
	 * <pre>
	 * 		{
	 * 			id : "-1",
	 * 			name : "-",
	 * 			childsArray : []
	 * 		}
	 * </pre>
	 */
	public Directory() {
		this.id 			= "-1";
		this.name 			= " - ";
		this.childsArray	= new ArrayList<ChildInfo>();
	}
	
	/**
	 * @return This directory ID
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @return This directory name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return This directory childs array
	 */
	public List<ChildInfo> getChildsArray() {
		return this.childsArray;
	}
	
	/**
	 * Sets this directory id
	 * @param id
	 * @return This directory
	 */
	public Directory setId(String id) {
		this.id = id;
		return this;
	}
	/**
	 * Sets this directory name
	 * @param name
	 * @return This directory
	 */
	public Directory setName(String name) {
		this.name = name;
		return this;
	}
	/**
	 * Sets this directory childs array
	 * @param childsArray
	 * @return This directory
	 */
	public Directory setChildsArray(List<ChildInfo> childsArray) {
		this.childsArray = childsArray;
		return this;
	}
	/**
	 * Adds a child to this directory childs array
	 * @param child
	 * @return This directory
	 */
	public Directory addChild(ChildInfo child) {
		this.childsArray.add(child);
		return this;
	}
	
}
