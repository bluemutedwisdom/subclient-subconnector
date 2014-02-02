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

import net.subclient.subsonic.deserializers.SearchResponseDeserializer;
import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.mappings.FolderInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SearchResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(SearchResponse.class, new SearchResponseDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.SearchResponse
	 */
	@Test
	public void testSearchResponse() {
		String mockFile = "SearchResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					SearchResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			SearchResponse resp = this.gson.fromJson(responseText, SearchResponse.class);
			
			//Assertions
			int expectedSongs 	= 5,
				expectedAlbums 	= 4,
				expectedArtists	= 2,
				i				= 0;
			String[] songTitles = new String[] {
				"Paul Oakenfold - Number 116", "Paul Oakenfold - Essential Mix In China", "Remember (Paul)", "Natasha Bedingfield - If You're Gonna Jump (Paul Oakenfold", "Paul Oakenfold - Global Underground - New York - CD1"
			};
			String[] albumTitles = new String[] {
				"Perfecto Podcast- featuring Paul Oakenfold", "Paul van Dyk's VONYC Sessions Podcast", "Paul Van Dyk - (2011) Vonyc Sessions 2011", "Paul Oakenfold - (2012) We are Planet Perfecto Vol 02"
			};
			String[] artists = new String[] {"Paul Oakenfold", "Paul Van Dyk"};
			assertEquals(expectedSongs, resp.getSearchResults().getSongsArray().size());
			assertEquals(expectedAlbums, resp.getSearchResults().getAlbumsArray().size());
			assertEquals(expectedArtists, resp.getSearchResults().getArtistsArray().size());
			for(ChildInfo song : resp.getSearchResults().getSongsArray()) {
				assertEquals(songTitles[i], song.getTitle());
				i++;
			}
			i = 0;
			for(ChildInfo album : resp.getSearchResults().getAlbumsArray()) {
				assertEquals(albumTitles[i], album.getTitle());
				i++;
			}
			i = 0;
			for(FolderInfo artist : resp.getSearchResults().getArtistsArray()) {
				assertEquals(artists[i], artist.getName());
				i++;
			}
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.SearchResponse
	 */
	@Test
	public void testSearchResponseMixed() {
		String mockFile = "SearchResponseMixedMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					SearchResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			SearchResponse resp = this.gson.fromJson(responseText, SearchResponse.class);
			
			//Assertions
			int expectedSongs 	= 1,
				expectedAlbums 	= 0,
				expectedArtists	= 2;
			assertEquals(expectedSongs, resp.getSearchResults().getSongsArray().size());
			assertEquals(expectedAlbums, resp.getSearchResults().getAlbumsArray().size());
			assertEquals(expectedArtists, resp.getSearchResults().getArtistsArray().size());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.SearchResponse
	 */
	@Test
	public void testSearchResponseEmpty() {
		String mockFile = "SearchResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					SearchResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			SearchResponse resp = this.gson.fromJson(responseText, SearchResponse.class);
			
			//Assertions
			assertEquals(0, resp.getSearchResults().getSongsArray().size());
			assertEquals(0, resp.getSearchResults().getAlbumsArray().size());
			assertEquals(0, resp.getSearchResults().getArtistsArray().size());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
