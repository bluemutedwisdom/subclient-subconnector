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

import net.subclient.subsonic.deserializers.GetPlaylistsResponseDeserializer;
import net.subclient.subsonic.mappings.PlaylistInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetPlaylistsResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(GetPlaylistsResponse.class, new GetPlaylistsResponseDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.GetPlaylistsResponse
	 */
	@Test
	public void testGetPlaylistsResponse() {
		String mockFile = "GetPlaylistsResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPlaylistsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPlaylistsResponse resp = this.gson.fromJson(responseText, GetPlaylistsResponse.class);
			
			//Assertions
			int expectedPlaylists	= 2,
					i					= 0;
			String[] owners = new String[] {"admin", "alejandro"};
			assertEquals(expectedPlaylists, resp.getPlaylists().getPlaylistsArray().size());
			for(PlaylistInfo playlist : resp.getPlaylists().getPlaylistsArray()) {
				assertEquals(owners[i], playlist.getOwner());
				i++;
			}
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetPlaylistsResponse
	 */
	@Test
	public void testGetPlaylistsResponseSingle() {
		String mockFile = "GetPlaylistsResponseSingleMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPlaylistsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPlaylistsResponse resp = this.gson.fromJson(responseText, GetPlaylistsResponse.class);
			
			//Assertions
			int expectedPlaylists	= 1;
			assertEquals(expectedPlaylists, resp.getPlaylists().getPlaylistsArray().size());
			assertEquals("alejandro", resp.getPlaylists().getPlaylistsArray().get(0).getOwner());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetPlaylistsResponse
	 */
	@Test
	public void testGetPlaylistsResponseEmpty() {
		String mockFile = "GetPlaylistsResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPlaylistsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPlaylistsResponse resp = this.gson.fromJson(responseText, GetPlaylistsResponse.class);
			
			//Assertions
			int expectedPlaylists	= 0;
			assertEquals(expectedPlaylists, resp.getPlaylists().getPlaylistsArray().size());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
