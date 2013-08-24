package net.subclient.subsonic.responses;

import static org.junit.Assert.*;

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
