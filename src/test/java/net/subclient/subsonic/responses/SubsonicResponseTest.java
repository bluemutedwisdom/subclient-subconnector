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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.util.FileUtils;
import net.subclient.subsonic.util.Version;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SubsonicResponseTest {

private Gson gson = null;
	
	/**
	 * Sets up the JSON parser
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder().create();
	}
	
	/**
	 * @see net.subclient.subsonic.responses.SubsonicResponse
	 */
	@Test
	public void testSubsonicResponse() {
		String mockFile = "SubsonicResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					SubsonicResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			SubsonicResponse resp = this.gson.fromJson(responseText, SubsonicResponse.class);
			Version expectedVersion = new Version(1, 8);
			
			//Assertions
			assertEquals(SubsonicResponse.STATUS_OK, resp.getStatus());
			assertNotSame(SubsonicResponse.STATUS_FAILED, resp.getStatus());
			assertTrue(expectedVersion.compareTo(resp.getVersion()) == 0);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		}
	}

}
