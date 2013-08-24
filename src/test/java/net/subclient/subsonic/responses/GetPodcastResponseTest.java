package net.subclient.subsonic.responses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetPodcastResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder().create();
	}

	/**
	 * net.subclient.subsonic.responses.GetPodcastResponse
	 */
	@Test
	public void testGetPodcastResponse() {
		String mockFile = "GetPodcastResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPodcastResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPodcastResponse resp = this.gson.fromJson(responseText, GetPodcastResponse.class);
			
			//Assertions
			int expectedEpisodesCount 	= 4;
			String expectedId 			= "0",
				   expectedTitle 		= "A State of Trance Official Podcast",
				   expectedStatus 		= "completed",
				   expectedDescription	= "Every week, Armin selects his favourite tunes of the A State of Trance radio show and puts them into his official Podcast. Expect a blend of the hottest in trance and progressive. Enjoy!";
			URL expectedUrl 			= new URL("http://podcast.armadamusic.com/asot/podcast.xml");
			assertEquals(expectedEpisodesCount, resp.getChannel().getEpisodesArray().size());
			assertEquals(expectedId, resp.getChannel().getId());
			assertEquals(expectedTitle, resp.getChannel().getTitle());
			assertEquals(expectedStatus, resp.getChannel().getStatus());
			assertEquals(expectedDescription, resp.getChannel().getDescription());
			assertEquals(expectedUrl, resp.getChannel().getUrl());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
