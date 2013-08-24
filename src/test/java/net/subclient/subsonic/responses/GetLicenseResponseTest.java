package net.subclient.subsonic.responses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.IndexInfoDeserializer;
import net.subclient.subsonic.mappings.IndexInfo;
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
