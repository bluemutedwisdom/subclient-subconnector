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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.GetAlbumsResponseDeserializer;
import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetAlbumsResponseTest {
	
	private Gson gson = null;
	
	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(GetAlbumsResponse.class, new GetAlbumsResponseDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.GetAlbumsResponse
	 */
	@Test
	public void testGetAlbumsResponse() {
		String mockFile = "GetAlbumsResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetAlbumsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetAlbumsResponse resp = this.gson.fromJson(responseText, GetAlbumsResponse.class);
			
			//Assertions
			int expectedAlbums 	= 10,
				i				= 0;
			String[] artists = new String[] {
					"virt, Freaky DNA and Norrin Radd", "Austin Wintory", "Paul Oakenfold", "Various Artists", "Moby", "Rob Zombie", "Calvin Carris", "Paul Van Dyk", "Deadmau5", "Tatu"
			};
			assertEquals(expectedAlbums, resp.getAlbums().getAlbumsArray().size());
			for(ChildInfo child : resp.getAlbums().getAlbumsArray()) {
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
	 * @see net.subclient.subsonic.responses.GetAlbumsResponse
	 */
	@Test
	public void testGetAlbumsResponseSingle() {
		String mockFile = "GetAlbumsResponseSingleMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetAlbumsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetAlbumsResponse resp = this.gson.fromJson(responseText, GetAlbumsResponse.class);
			
			//Assertions
			int expectedAlbums = 1;
			assertEquals(expectedAlbums, resp.getAlbums().getAlbumsArray().size());
			assertEquals("Foo Fighters", resp.getAlbums().getAlbumsArray().get(0).getArtist());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
