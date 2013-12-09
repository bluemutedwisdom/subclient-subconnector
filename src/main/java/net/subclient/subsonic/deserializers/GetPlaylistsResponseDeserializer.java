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

package net.subclient.subsonic.deserializers;

import java.lang.reflect.Type;

import net.subclient.subsonic.mappings.Playlists;
import net.subclient.subsonic.responses.GetPlaylistsResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * This class specifies how to deserialize a {@link net.subclient.subsonic.responses.GetPlaylistsResponse GetPlaylistsResponse} object.
 * It happens that the response could contain an object with name "playlists" that is a JSON Array when it has more than one child, but it is a JSON Object when there is only one child.
 * <ul>
 * 		<li>
 * 			This is an example of a playlists object with multiple childs.
 *		</li>
 * 			<pre>
 * 				{
 * 					playlists : [
 * 						{
 * 							//CHILD_ONE
 * 						},
 * 						{
 * 							//CHILD_TWO
 * 						},
 * 						{
 * 							//CHILD_THREE
 * 						}
 * 					]
 * 				}
 * 			</pre>
 *		<li>
 *			This is an example of a playlists object with just one child
 * 			<pre>
 * 				{
 * 					playlists : {
 * 						//CHILD_ONE
 * 					}
 * 				}
 * 			</pre>
 *		</li>
 * </ul>
 * This class takes the single child, creates an array and returns an array with that child in those cases.
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class GetPlaylistsResponseDeserializer implements JsonDeserializer<GetPlaylistsResponse> {
	
	private Gson gson = null;
	
	public GetPlaylistsResponseDeserializer() {
		this.gson = new GsonBuilder().registerTypeHierarchyAdapter(Playlists.class, new PlaylistsDeserializer()).create();
	}
	
	@Override
	public GetPlaylistsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		GetPlaylistsResponse response	= null;
		Playlists playlists				= null;
		JsonElement playlistsElement	= json.getAsJsonObject().get("playlists");
		String status					= json.getAsJsonObject().get("status").getAsString(),
			   version					= json.getAsJsonObject().get("version").getAsString();
		
		if(playlistsElement.isJsonObject()) {
			playlists = this.gson.fromJson(playlistsElement, Playlists.class);			
		} else
			playlists = new Playlists();
		
		response = new GetPlaylistsResponse();
		response.setPlaylists(playlists)
				.setStatus(status)
				.setVersion(version);
		return response;
	}

}
