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

import net.subclient.subsonic.deserializers.PlaylistInfoDeserializer;
import net.subclient.subsonic.mappings.PlaylistInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetPlaylistResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(PlaylistInfo.class, new PlaylistInfoDeserializer())
		  .create();
	}

	/**
	 * net.subclient.subsonic.responses.GetPlaylistResponse
	 */
	@Test
	public void testGetPlaylistResponse() {
		String mockFile = "GetPlaylistResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPlaylistResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPlaylistResponse resp = this.gson.fromJson(responseText, GetPlaylistResponse.class);
			
			//Assertions
			int expectedDuration 	= 2927,
				expectedSongCount 	= 14;
			String expectedCreated 	= "2013-04-05T11:27:54",
				   expectedId		= "17",
				   expectedName 	= "Amaranthe. The Nexus",
				   expectedOwner	= "alejandro";
			boolean expectedPublic	= false;
			assertEquals(expectedDuration, resp.getPlaylist().getDuration());
			assertEquals(expectedSongCount, resp.getPlaylist().getSongCount());
			assertEquals(expectedSongCount, resp.getPlaylist().getEntriesArray().size());
			assertEquals(expectedCreated, resp.getPlaylist().getCreationDate());
			assertEquals(expectedId, resp.getPlaylist().getId());
			assertEquals(expectedName, resp.getPlaylist().getName());
			assertEquals(expectedOwner, resp.getPlaylist().getOwner());
			assertEquals(expectedPublic, resp.getPlaylist().isPublic());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * net.subclient.subsonic.responses.GetPlaylistResponse
	 */
	@Test
	public void testGetPlaylistResponseSingle() {
		String mockFile = "GetPlaylistResponseSingleMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPlaylistResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPlaylistResponse resp = this.gson.fromJson(responseText, GetPlaylistResponse.class);
			
			//Assertions
			int expectedDuration 	= 188,
				expectedSongCount 	= 1;
			String expectedCreated 	= "2013-04-05T11:39:22",
				   expectedId		= "19",
				   expectedName 	= "Single",
				   expectedOwner	= "alejandro";
			boolean expectedPublic	= false;
			assertEquals(expectedDuration, resp.getPlaylist().getDuration());
			assertEquals(expectedSongCount, resp.getPlaylist().getSongCount());
			assertEquals(expectedSongCount, resp.getPlaylist().getEntriesArray().size());
			assertEquals(expectedCreated, resp.getPlaylist().getCreationDate());
			assertEquals(expectedId, resp.getPlaylist().getId());
			assertEquals(expectedName, resp.getPlaylist().getName());
			assertEquals(expectedOwner, resp.getPlaylist().getOwner());
			assertEquals(expectedPublic, resp.getPlaylist().isPublic());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * net.subclient.subsonic.responses.GetPlaylistResponse
	 */
	@Test
	public void testGetPlaylistResponseEmpty() {
		String mockFile = "GetPlaylistResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPlaylistResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPlaylistResponse resp = this.gson.fromJson(responseText, GetPlaylistResponse.class);
			
			//Assertions
			int expectedDuration 	= 0,
				expectedSongCount 	= 0;
			String expectedCreated 	= "2013-04-05T11:37:17",
				   expectedId		= "18",
				   expectedName 	= "Empty",
				   expectedOwner	= "alejandro";
			boolean expectedPublic	= false;
			assertEquals(expectedDuration, resp.getPlaylist().getDuration());
			assertEquals(expectedSongCount, resp.getPlaylist().getSongCount());
			assertEquals(expectedSongCount, resp.getPlaylist().getEntriesArray().size());
			assertEquals(expectedCreated, resp.getPlaylist().getCreationDate());
			assertEquals(expectedId, resp.getPlaylist().getId());
			assertEquals(expectedName, resp.getPlaylist().getName());
			assertEquals(expectedOwner, resp.getPlaylist().getOwner());
			assertEquals(expectedPublic, resp.getPlaylist().isPublic());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
