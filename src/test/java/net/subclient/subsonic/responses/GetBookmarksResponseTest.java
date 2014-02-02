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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.BookmarkDeserializer;
import net.subclient.subsonic.deserializers.GetBookmarksResponseDeserializer;
import net.subclient.subsonic.mappings.Bookmark;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetBookmarksResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder().registerTypeHierarchyAdapter(GetBookmarksResponse.class	, new GetBookmarksResponseDeserializer())
									 .registerTypeHierarchyAdapter(Bookmark.class				, new BookmarkDeserializer())
									 .create();
	}
	
	@Test
	public void testGetBookmarksResponse() {
		String mockFile = "GetBookmarksResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetBookmarksResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetBookmarksResponse resp = this.gson.fromJson(responseText, GetBookmarksResponse.class);
			
			//Assertions
			assertEquals(2, resp.getBookmarks().getBookmarksArray().size());
			assertTrue(resp.getBookmarks().getBookmarksArray().get(0).getPosition() == 26096);
			assertTrue(resp.getBookmarks().getBookmarksArray().get(1).getPosition() == 292415);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	
	@Test
	public void testGetBookmarksResponseSingle() {
		String mockFile = "GetBookmarksResponseSingleMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetBookmarksResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetBookmarksResponse resp = this.gson.fromJson(responseText, GetBookmarksResponse.class);
			
			//Assertions
			assertEquals(1, resp.getBookmarks().getBookmarksArray().size());
			assertTrue(resp.getBookmarks().getBookmarksArray().get(0).getPosition() == 26096);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	
	@Test
	public void testGetBookmarksResponseEmpty() {
		String mockFile = "GetBookmarksResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetBookmarksResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetBookmarksResponse resp = this.gson.fromJson(responseText, GetBookmarksResponse.class);
			
			//Assertions
			assertEquals(0, resp.getBookmarks().getBookmarksArray().size());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	
}