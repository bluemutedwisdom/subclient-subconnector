/*
 
 This file is part of Subconnector.
 import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.DirectoryDeserializer;
import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.mappings.Directory;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
E. See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with Subconnector. If not, see <http://www.gnu.org/licenses/>.
 
 Copyright 2012 - 2014 Alejandro Celaya Alastrué
 
 */

package net.subclient.subsonic.responses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.DirectoryDeserializer;
import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.mappings.Directory;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetMusicDirectoryResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(Directory.class, new DirectoryDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.GetMusicDirectoryResponse
	 */
	@Test
	public void testGetMusicDirectoryResponseMultipleAlbums() {
		String mockFile = "GetMusicDirectoryResponseMultipleAlbumsMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicDirectoryResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicDirectoryResponse resp = this.gson.fromJson(responseText, GetMusicDirectoryResponse.class);
			
			//Assertions
			int albums 			= 0,
				expectedAlbums	= 8,
				songs 			= 0,
				expectedSongs 	= 0,
				expectedSize 	= expectedSongs + expectedAlbums;
			assertEquals(expectedSize, resp.getDirectory().getChildsArray().size());
			assertEquals("19", resp.getDirectory().getId());
			assertEquals("Alanis Morissette", resp.getDirectory().getName());
			//Only albums should be found
			for(ChildInfo child : resp.getDirectory().getChildsArray()) {
				if(child.isDir()) 	albums++;
				else				songs++;
			}
			assertEquals(expectedAlbums, albums);
			assertEquals(expectedSongs, songs);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetMusicDirectoryResponse
	 */
	@Test
	public void testGetMusicDirectoryResponseMultipleSongs() {
		String mockFile = "GetMusicDirectoryResponseMultipleSongsMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicDirectoryResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicDirectoryResponse resp = this.gson.fromJson(responseText, GetMusicDirectoryResponse.class);
			
			//Assertions
			int albums 			= 0,
				expectedAlbums	= 0,
				songs 			= 0,
				expectedSongs 	= 12,
				expectedSize 	= expectedSongs + expectedAlbums;
			assertEquals(expectedSize, resp.getDirectory().getChildsArray().size());
			assertEquals("46", resp.getDirectory().getId());
			assertEquals("Alanis Morissette - (1994) Jagged Little Pill", resp.getDirectory().getName());
			//Only songs should be found
			for(ChildInfo child : resp.getDirectory().getChildsArray()) {
				if(child.isDir()) 	albums++;
				else				songs++;
			}
			assertEquals(expectedAlbums, albums);
			assertEquals(expectedSongs, songs);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetMusicDirectoryResponse
	 */
	@Test
	public void testGetMusicDirectoryResponseEmpty() {
		String mockFile = "GetMusicDirectoryResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicDirectoryResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicDirectoryResponse resp = this.gson.fromJson(responseText, GetMusicDirectoryResponse.class);
			
			//Assertions
			assertEquals(0, resp.getDirectory().getChildsArray().size());
			assertEquals("22", resp.getDirectory().getId());
			assertEquals("AC.DC", resp.getDirectory().getName());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetMusicDirectoryResponse
	 */
	@Test
	public void testGetMusicDirectoryResponseMixed() {
		String mockFile = "GetMusicDirectoryResponseMixedMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicDirectoryResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicDirectoryResponse resp = this.gson.fromJson(responseText, GetMusicDirectoryResponse.class);
			
			//Assertions
			int albums 			= 0,
				expectedAlbums	= 4,
				songs 			= 0,
				expectedSongs 	= 13,
				expectedSize 	= expectedSongs + expectedAlbums;
			assertEquals(expectedSize, resp.getDirectory().getChildsArray().size());
			assertEquals("19", resp.getDirectory().getId());
			assertEquals("Alanis Morissette", resp.getDirectory().getName());
			for(ChildInfo child : resp.getDirectory().getChildsArray()) {
				if(child.isDir()) 	albums++;
				else				songs++;
			}
			assertEquals(expectedAlbums, albums);
			assertEquals(expectedSongs, songs);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetMusicDirectoryResponse
	 */
	@Test
	public void testGetMusicDirectoryResponseSingleAlbum() {
		String mockFile = "GetMusicDirectoryResponseSingleAlbumMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicDirectoryResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicDirectoryResponse resp = this.gson.fromJson(responseText, GetMusicDirectoryResponse.class);
			
			//Assertions
			int albums 			= 0,
					expectedAlbums	= 1,
					songs 			= 0,
					expectedSongs 	= 0,
					expectedSize 	= expectedSongs + expectedAlbums;
			assertEquals(expectedSize, resp.getDirectory().getChildsArray().size());
			assertEquals("19", resp.getDirectory().getId());
			assertEquals("Alanis Morissette", resp.getDirectory().getName());
			for(ChildInfo child : resp.getDirectory().getChildsArray()) {
				if(child.isDir()) 	albums++;
				else				songs++;
			}
			assertEquals(expectedAlbums, albums);
			assertEquals(expectedSongs, songs);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetMusicDirectoryResponse
	 */
	@Test
	public void testGetMusicDirectoryResponseSingleSong() {
		String mockFile = "GetMusicDirectoryResponseSingleSongMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetMusicDirectoryResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetMusicDirectoryResponse resp = this.gson.fromJson(responseText, GetMusicDirectoryResponse.class);
			
			//Assertions
			int albums 			= 0,
				expectedAlbums	= 0,
				songs 			= 0,
				expectedSongs 	= 1,
				expectedSize 	= expectedSongs + expectedAlbums;
			assertEquals(expectedSize, resp.getDirectory().getChildsArray().size());
			assertEquals("19", resp.getDirectory().getId());
			assertEquals("Alanis Morissette", resp.getDirectory().getName());
			for(ChildInfo child : resp.getDirectory().getChildsArray()) {
				if(child.isDir()) 	albums++;
				else				songs++;
			}
			assertEquals(expectedAlbums, albums);
			assertEquals(expectedSongs, songs);
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
