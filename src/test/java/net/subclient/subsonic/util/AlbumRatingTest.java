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

package net.subclient.subsonic.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AlbumRatingTest {

	@Test
	public void testValidRating() {
		AlbumRating rating = new AlbumRating("34", 8);
		assertEquals(5, rating.getRating());
		
		rating.setRating(-10);
		assertEquals(0, rating.getRating());
	}

}
