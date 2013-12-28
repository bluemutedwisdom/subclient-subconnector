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

package net.subclient.subsonic.responses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.GetRandomSongsResponseDeserializer;
import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetRandomSongsResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(GetRandomSongsResponse.class, new GetRandomSongsResponseDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.GetRandomSongsResponse
	 */
	@Test
	public void testGetRandomSongsResponse() {
		String mockFile = "GetRandomSongsResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetRandomSongsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetRandomSongsResponse resp = this.gson.fromJson(responseText, GetRandomSongsResponse.class);
			
			//Assertions
			int expectedSongs 	= 10,
				i				= 0;
			String[] artists = new String[] {
				"Josh Whelchel", "Evanescence", "virt, Freaky DNA and Norrin Radd", "Linkin Park", "Laura Shigihara", "Marilyn Manson", "Deadmau5", "Binary Finary", "Evanescence", "Evanescence"
			};
			assertEquals(expectedSongs, resp.getRandomSongs().getRandomSongsArray().size());
			for(ChildInfo child : resp.getRandomSongs().getRandomSongsArray()) {
				assertEquals(artists[i], child.getArtist());
				i++;
			}
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetRandomSongsResponse
	 */
	@Test
	public void testGetRandomSongsResponseSingle() {
		String mockFile = "GetRandomSongsResponseSingleMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetRandomSongsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetRandomSongsResponse resp = this.gson.fromJson(responseText, GetRandomSongsResponse.class);
			
			//Assertions
			int expectedSongs = 1;
			assertEquals(expectedSongs, resp.getRandomSongs().getRandomSongsArray().size());
			assertEquals("Madcore", resp.getRandomSongs().getRandomSongsArray().get(0).getArtist());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetRandomSongsResponse
	 */
	@Test
	public void testGetRandomSongsResponseEmpty() {
		String mockFile = "GetRandomSongsResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetRandomSongsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetRandomSongsResponse resp = this.gson.fromJson(responseText, GetRandomSongsResponse.class);
			
			//Assertions
			int expectedSongs = 0;
			assertEquals(expectedSongs, resp.getRandomSongs().getRandomSongsArray().size());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
