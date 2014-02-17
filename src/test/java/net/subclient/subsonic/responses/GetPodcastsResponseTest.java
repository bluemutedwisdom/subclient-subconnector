/*
 
 This file is part of Subconnector.
 import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.GetPodcastsResponseDeserializer;
import net.subclient.subsonic.mappings.ChannelInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
LITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with Subconnector. If not, see <http://www.gnu.org/licenses/>.
 
 Copyright 2012 - 2014 Alejandro Celaya Alastrué
 
 */

package net.subclient.subsonic.responses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URLDecoder;

import net.subclient.subsonic.deserializers.GetPodcastsResponseDeserializer;
import net.subclient.subsonic.mappings.ChannelInfo;
import net.subclient.subsonic.util.FileUtils;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetPodcastsResponseTest {
	
	private Gson gson = null;

	@Before
	public void setUp() throws Exception {
		this.gson = new GsonBuilder()
		  .registerTypeHierarchyAdapter(GetPodcastsResponse.class, new GetPodcastsResponseDeserializer())
		  .create();
	}

	/**
	 * @see net.subclient.subsonic.responses.GetPodcastsResponse
	 */
	@Test
	public void testGetPodcastsResponse() {
		String mockFile = "GetPodcastsResponseMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPodcastsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPodcastsResponse resp = this.gson.fromJson(responseText, GetPodcastsResponse.class);
			
			//Assertions
			int expectedChannels	= 6,
				i					= 0;
			String[] titles = new String[] {
				"Music For Programming", "Corsten's Countdown Official Podcast by FLAIX.fr", "Ti&#235;sto`s Club Life Podcast", "Perfecto Podcast: featuring Paul Oakenfold", 
				"Paul van Dyk's VONYC Sessions Podcast", "A State of Trance Official Podcast"
			};
			assertEquals(expectedChannels, resp.getPodcasts().getChannelsArray().size());
			for(ChannelInfo channel : resp.getPodcasts().getChannelsArray()) {
				assertEquals(titles[i], channel.getTitle());
				i++;
			}
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			e.printStackTrace();
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetPodcastsResponse
	 */
	@Test
	public void testGetPodcastsResponseSingle() {
		String mockFile = "GetPodcastsResponseSingleMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPodcastsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPodcastsResponse resp = this.gson.fromJson(responseText, GetPodcastsResponse.class);
			
			//Assertions
			assertEquals(1, resp.getPodcasts().getChannelsArray().size());
			assertEquals("A State of Trance Official Podcast", resp.getPodcasts().getChannelsArray().get(0).getTitle());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			e.printStackTrace();
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}
	/**
	 * @see net.subclient.subsonic.responses.GetPodcastsResponse
	 */
	@Test
	public void testGetPodcastsResponseEmpty() {
		String mockFile = "GetPodcastsResponseEmptyMock.json";
		try {
			//Read response file
			String responseText = FileUtils.readTextFile(
				URLDecoder.decode(
					GetPodcastsResponseTest.class.getResource("../mocks/" + mockFile).getPath(), 
					"UTF-8"
				)
			);
			//Cast response
			GetPodcastsResponse resp = this.gson.fromJson(responseText, GetPodcastsResponse.class);
			
			//Assertions
			assertEquals(0, resp.getPodcasts().getChannelsArray().size());
		} catch (IOException e) {
			fail(String.format("IOException while opening %s", mockFile));
		} catch (Exception e) {
			fail(String.format("Exception of type %s thrown", e.getClass().getName()));
		}
	}

}
