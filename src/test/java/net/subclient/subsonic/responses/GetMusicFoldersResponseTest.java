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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.GetMusicFoldersResponseDeserializer;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetMusicFoldersResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(GetMusicFoldersResponse.class, new GetMusicFoldersResponseDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.GetMusicFoldersResponse
	 */
	@Test
	public void testGetMusicFoldersResponse() {
		String mockFile = "GetMusicFoldersResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicFoldersResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicFoldersResponse resp = this.gson.fromJson(responseText, GetMusicFoldersResponse.class);
			
			//Assertions
			assertTrue(resp.getMusicFolders().getMusicFoldersArray().size() == 2);
			assertEquals(resp.getMusicFolders().getMusicFoldersArray().get(0).getName(), "Music");
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetMusicFoldersResponse
	 */
	@Test
	public void testGetMusicFoldersResponseEmpty() {
		String mockFile = "GetMusicFoldersResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicFoldersResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicFoldersResponse resp = this.gson.fromJson(responseText, GetMusicFoldersResponse.class);
			
			//Assertions
			assertTrue(resp.getMusicFolders().getMusicFoldersArray().size() == 0);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetMusicFoldersResponse
	 */
	@Test
	public void testGetMusicFoldersResponseSingleEmpty() {
		String mockFile = "GetMusicFoldersResponseSingleMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicFoldersResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicFoldersResponse resp = this.gson.fromJson(responseText, GetMusicFoldersResponse.class);
			
			//Assertions
			assertTrue(resp.getMusicFolders().getMusicFoldersArray().size() == 1);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
