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

import net.subclient.subsonic.deserializers.IndexInfoDeserializer;
import net.subclient.subsonic.mappings.IndexInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetIndexesResponseTest {
	
	private Gson gson = null;
	
	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(IndexInfo.class, new IndexInfoDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.GetIndexesResponse
	 */
	@Test
	public void testGetIndexesResponseRegular() {
		String mockFile = "GetIndexesResponseRegularMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetIndexesResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetIndexesResponse resp = this.gson.fromJson(responseText, GetIndexesResponse.class);
			long lastModifiedExpected = 1363553853000L;
			
			//Assertions
			assertEquals(lastModifiedExpected, resp.getIndexes().getLastModified());
			assertTrue(resp.getIndexes().getIndexesArray().size() == 17);
			IndexInfo infoD = resp.getIndexes().getIndexesArray().get(3);
			assertTrue(infoD.getName().equalsIgnoreCase("D"));
			assertTrue(infoD.getArtistsArray().size() == 4);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetIndexesResponse
	 */
	@Test
	public void testGetIndexesResponseComplete() {
		String mockFile = "GetIndexesResponseCompleteMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetIndexesResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetIndexesResponse resp = this.gson.fromJson(responseText, GetIndexesResponse.class);
			long lastModifiedExpected = 1363553853000L;
			
			//Assertions
			assertEquals(lastModifiedExpected, resp.getIndexes().getLastModified());
			assertTrue(resp.getIndexes().getIndexesArray().size() == 22);
			IndexInfo infoD = resp.getIndexes().getIndexesArray().get(6);
			assertTrue(infoD.getName().equalsIgnoreCase("G"));
			assertTrue(infoD.getArtistsArray().size() == 1);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetIndexesResponse
	 */
	@Test
	public void testGetIndexesResponseEmpty() {
		String mockFile = "GetIndexesResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetIndexesResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetIndexesResponse resp = this.gson.fromJson(responseText, GetIndexesResponse.class);
			long lastModifiedExpected = 1363553853000L;
			
			//Assertions
			assertEquals(lastModifiedExpected, resp.getIndexes().getLastModified());
			assertTrue(resp.getIndexes().getIndexesArray().size() == 0);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
