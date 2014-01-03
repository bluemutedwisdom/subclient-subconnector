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
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.GetStarredResponseDeserializer;
import net.subclient.subsonic.deserializers.StarredDeserializer;
import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.mappings.FolderInfo;
import net.subclient.subsonic.mappings.Starred;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetStarredResponseTest {

	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder().registerTypeHierarchyAdapter(Starred.class, new StarredDeserializer())
									 .registerTypeHierarchyAdapter(GetStarredResponse.class, new GetStarredResponseDeserializer())
									 .create();
	}
	
	@Test
	public void testGetStarredResponse() {
		String mockFile = "GetStarredResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetStarredResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			
			GetStarredResponse resp = this.gson.fromJson(responseText, GetStarredResponse.class);
			
			int expectedSongs 	= 3,
				expectedAlbums 	= 2,
				expectedArtists	= 2,
				i				= 0;
			
			// Assert array sizes
			assertEquals(expectedSongs, resp.getStarred().getSongsArray().size());
			assertEquals(expectedAlbums, resp.getStarred().getAlbumsArray().size());
			assertEquals(expectedArtists, resp.getStarred().getArtistsArray().size());
			
			// Assert array contents
			String[] songTitles = new String[] {
				"Bodyrox & Luciana - What Planet Are You On? (deadmau5 Remix)", 
				"James Talk - Remote (deadmau5 Remix)", 
				"NuBreed - Nufunk (deadmau5 Remix)"
			};
			String[] albumTitles = new String[] {
				"Daft Punk - (2010) The Third Twin - Homemade", 
				"Daft Punk - (2005) Human After All"
			};
			String[] artists = new String[] {"Armin Van Buuren", "Amaranthe"};
			
			for(ChildInfo song : resp.getStarred().getSongsArray()) {
				assertEquals(songTitles[i], song.getTitle());
				i++;
			}
			i = 0;
			for(ChildInfo album : resp.getStarred().getAlbumsArray()) {
				assertEquals(albumTitles[i], album.getTitle());
				i++;
			}
			i = 0;
			for(FolderInfo artist : resp.getStarred().getArtistsArray()) {
				assertEquals(artists[i], artist.getName());
				i++;
			}
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	
	@Test
	public void testGetStarredResponseMixed() {
		String mockFile = "GetStarredResponseMixedMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetStarredResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			
			GetStarredResponse resp = this.gson.fromJson(responseText, GetStarredResponse.class);
			
			int expectedSongs 	= 3,
				expectedAlbums 	= 0,
				expectedArtists	= 1,
				i				= 0;
			
			// Assert array sizes
			assertEquals(expectedSongs, resp.getStarred().getSongsArray().size());
			assertEquals(expectedAlbums, resp.getStarred().getAlbumsArray().size());
			assertEquals(expectedArtists, resp.getStarred().getArtistsArray().size());
			
			// Assert array contents
			String[] songTitles = new String[] {
				"Bodyrox & Luciana - What Planet Are You On? (deadmau5 Remix)", 
				"James Talk - Remote (deadmau5 Remix)", 
				"NuBreed - Nufunk (deadmau5 Remix)"
			};
			String[] artists = new String[] {"Armin Van Buuren"};
			
			for(ChildInfo song : resp.getStarred().getSongsArray()) {
				assertEquals(songTitles[i], song.getTitle());
				i++;
			}
			i = 0;
			for(FolderInfo artist : resp.getStarred().getArtistsArray()) {
				assertEquals(artists[i], artist.getName());
				i++;
			}
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	
	@Test
	public void testGetStarredResponseEmpty() {
		String mockFile = "GetStarredResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetStarredResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			
			GetStarredResponse resp = this.gson.fromJson(responseText, GetStarredResponse.class);
			
			int expectedSongs 	= 0,
				expectedAlbums 	= 0,
				expectedArtists	= 0;
			
			// Assert array sizes
			assertEquals(expectedSongs, resp.getStarred().getSongsArray().size());
			assertEquals(expectedAlbums, resp.getStarred().getAlbumsArray().size());
			assertEquals(expectedArtists, resp.getStarred().getArtistsArray().size());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	
}
