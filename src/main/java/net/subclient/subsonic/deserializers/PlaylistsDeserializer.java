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
import java.util.ArrayList;

import net.subclient.subsonic.mappings.PlaylistInfo;
import net.subclient.subsonic.mappings.Playlists;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * This class specifies how to deserialize a {@link net.subclient.subsonic.mappings.Playlists Playlists} object.
 * It happens that the response could contain an object with name "playlist" that is a JSON Array when it has more than one child, but it is a JSON Object when there is only one child.
 * <ul>
 * 		<li>
 * 			This is an example of a playlist object with multiple childs.
 *		</li>
 * 			<pre>
 * 				{
 * 					playlist : [
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
 *			This is an example of a playlist object with just one child
 * 			<pre>
 * 				{
 * 					playlist : {
 * 						//CHILD_ONE
 * 					}
 * 				}
 * 			</pre>
 *		</li>
 * </ul>
 * This class takes the single child, creates an array and returns an array with that child in those cases.
 * @author Alejandro Celaya Alastrué
 */
public class PlaylistsDeserializer implements JsonDeserializer<Playlists> {
	
	private Gson gson = new Gson();
	
	@Override
	public Playlists deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Playlists playlists 					= null;
		ArrayList<PlaylistInfo> playlistsArray	= null;
		JsonElement playlistsElement			= json.getAsJsonObject().get("playlist");
		
		if(playlistsElement.isJsonArray()) 
			playlistsArray = gson.fromJson(playlistsElement, new TypeToken<ArrayList<PlaylistInfo>>(){}.getType());			
		else {
			playlistsArray = new ArrayList<PlaylistInfo>();
			PlaylistInfo childInfo = gson.fromJson(playlistsElement, PlaylistInfo.class);
			playlistsArray.add(childInfo);
		}
		
		playlists = new Playlists();
		playlists.setPlaylistsArray(playlistsArray);
		return playlists;
	}

}
