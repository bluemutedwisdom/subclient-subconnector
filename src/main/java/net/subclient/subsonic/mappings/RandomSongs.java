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
 * Java representation of the "randomSongs" JSON Object.
 * @author Alejandro Celaya Alastrué
 */
public class RandomSongs {
	
	/** Songs array of this RandomSongs object */
	@SerializedName("song")
	private ArrayList<ChildInfo> songsArray;
	
	/**
	 * Constructs a new RandomSongs object with an empty songs array
	 */
	public RandomSongs() {
		this.songsArray = new ArrayList<ChildInfo>();
	}
	
	public ArrayList<ChildInfo> getRandomSongsArray() {
		return this.songsArray;
	}
	
	public RandomSongs setRandomSongsArray(ArrayList<ChildInfo> songsArray) {
		this.songsArray = songsArray;
		return this;
	}
	public RandomSongs addRandomSong(ChildInfo randomSong) {
		this.songsArray.add(randomSong);
		return this;
	}
	
}
