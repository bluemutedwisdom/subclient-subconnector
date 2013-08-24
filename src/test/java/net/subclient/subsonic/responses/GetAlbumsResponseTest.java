package net.subclient.subsonic.responses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URLDecoder;
import net.subclient.subsonic.deserializers.GetAlbumsResponseDeserializer;
import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetAlbumsResponseTest {
	
	private Gson gson = null;
	
	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(GetAlbumsResponse.class, new GetAlbumsResponseDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.GetAlbumsResponse
	 */
	@Test
	public void testGetAlbumsResponse() {
		String mockFile = "GetAlbumsResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetAlbumsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetAlbumsResponse resp = this.gson.fromJson(responseText, GetAlbumsResponse.class);
			
			//Assertions
			int expectedAlbums 	= 10,
				i				= 0;
			String[] artists = new String[] {
					"virt, Freaky DNA and Norrin Radd", "Austin Wintory", "Paul Oakenfold", "Various Artists", "Moby", "Rob Zombie", "Calvin Carris", "Paul Van Dyk", "Deadmau5", "Tatu"
			};
			assertEquals(expectedAlbums, resp.getAlbums().getAlbumsArray().size());
			for(ChildInfo child : resp.getAlbums().getAlbumsArray()) {
				assertEquals(artists[i], child.getArtist());
				i++;
			}
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetAlbumsResponse
	 */
	@Test
	public void testGetAlbumsResponseSingle() {
		String mockFile = "GetAlbumsResponseSingleMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetAlbumsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetAlbumsResponse resp = this.gson.fromJson(responseText, GetAlbumsResponse.class);
			
			//Assertions
			int expectedAlbums = 1;
			assertEquals(expectedAlbums, resp.getAlbums().getAlbumsArray().size());
			assertEquals("Foo Fighters", resp.getAlbums().getAlbumsArray().get(0).getArtist());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
