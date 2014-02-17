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

import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetLicenseResponseTest {
	
	private Gson gson = null;
	
	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .create();
	}

	/**
	 * see net.subclient.subsonic.responses.GetLicenseResponse
	 */
	@Test
	public void testGetLicenseValid() {
		String mockFile = "GetLicenseResponseValidMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetLicenseResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetLicenseResponse resp = this.gson.fromJson(responseText, GetLicenseResponse.class);
			
			//Assertions
			boolean expectedValidity	= true;
			String expectedEmail		= "foobar@gmail.com",
				   expectedDate 		= "2012-04-10T22:54:44",
				   expectedKey			= "8843d7f92416211de9ebb963ff4ce28125932878";
			assertEquals(expectedValidity, resp.getSubsonicLicense().isValid());
			assertEquals(expectedEmail, resp.getSubsonicLicense().getEmail());
			assertEquals(expectedDate, resp.getSubsonicLicense().getDate());
			assertEquals(expectedKey, resp.getSubsonicLicense().getKey());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * see net.subclient.subsonic.responses.GetLicenseResponse
	 */
	@Test
	public void testGetLicenseInvalid() {
		String mockFile = "GetLicenseResponseInvalidMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetLicenseResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetLicenseResponse resp = this.gson.fromJson(responseText, GetLicenseResponse.class);
			
			//Assertions
			boolean expectedValidity	= false;
			String expectedEmail		= "john.doe@domain.com",
				   expectedDate 		= "",
				   expectedKey 			= "";
			assertEquals(expectedValidity, resp.getSubsonicLicense().isValid());
			assertEquals(expectedEmail, resp.getSubsonicLicense().getEmail());
			assertEquals(expectedDate, resp.getSubsonicLicense().getDate());
			assertEquals(expectedKey, resp.getSubsonicLicense().getKey());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
