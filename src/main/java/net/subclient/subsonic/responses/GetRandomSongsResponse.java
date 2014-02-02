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

package net.subclient.subsonic.responses;

import net.subclient.subsonic.mappings.RandomSongs;

/**
 * Object returned on {@link net.subclient.subsonic.Connection#getRandomSongs() getRandomSongs} calls
 * @see net.subclient.subsonic.Connection
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class GetRandomSongsResponse extends SubsonicResponse {
	
	/** RandomSongs wrapper object */
	private RandomSongs randomSongs;
	
	/**
	 * Constructs a new GetRandomSongsResponse with a default constructed RandomSongs object
	 */
	public GetRandomSongsResponse() {
		super();
		this.randomSongs = new RandomSongs();
	}
	
	public RandomSongs getRandomSongs() {
		return this.randomSongs;
	}
	
	public GetRandomSongsResponse setRandomSongs(RandomSongs randomSongs) {
		this.randomSongs = randomSongs;
		return this;
	}
	
}
