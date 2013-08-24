package net.subclient.subsonic.util;

import static org.junit.Assert.*;

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
