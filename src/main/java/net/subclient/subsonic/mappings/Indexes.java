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

import com.google.gson.annotations.SerializedName;

/**
 * Java representation of the "indexes" JSON object
 * @author Alejandro Celaya Alastrué
 */
public class Indexes {
	
	/** Array of indexes of this object */
	@SerializedName("index")
	private ArrayList<IndexInfo> indexesArray;
	/** Last time in milliseconds the indexes were modified */
	private long lastModified;
	
	/**
	 * Constructs a new Indexes object with default values:
	 * <pre>
	 * 		{
	 * 			indexesArray : [],
	 * 			lastModified : 0
	 * 		}
	 * </pre>
	 */
	public Indexes() {
		this.indexesArray 	= new ArrayList<IndexInfo>();
		this.lastModified	= 0;
	}
	
	public ArrayList<IndexInfo> getIndexesArray() {
		return this.indexesArray;
	}
	public long getLastModified() {
		return this.lastModified;
	}
	
	public Indexes setIndexesArray(ArrayList<IndexInfo> indexes) {
		this.indexesArray = indexes;
		return this;
	}
	public Indexes addIndex(IndexInfo index) {
		this.indexesArray.add(index);
		return this;
	}
	public Indexes setLastModified(long lastModified) {
		this.lastModified = lastModified;
		return this;
	}
	
}
