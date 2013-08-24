package net.subclient.subsonic.responses;

import static org.junit.Assert.*;

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
