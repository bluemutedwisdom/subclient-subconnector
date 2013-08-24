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

import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.mappings.PlaylistInfo;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * This class specifies how to deserialize a {@link net.subclient.subsonic.mappings.PlaylistInfo PlaylistInfo} object.
 * It happens that the response could contain an object with name "entry" that is a JSON Array when it has more than one child, but it is a JSON Object when there is only one child.
 * <ul>
 * 		<li>
 * 			This is an example of an entry object with multiple childs.
 *		</li>
 * 			<pre>
 * 				{
 * 					entry : [
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
 *			This is an example of an entry object with just one child
 * 			<pre>
 * 				{
 * 					entry : {
 * 						//CHILD_ONE
 * 					}
 * 				}
 * 			</pre>
 *		</li>
 * </ul>
 * This class takes the single child, creates an array and returns an array with that child in those cases.
 * @author Alejandro Celaya Alastrué
 */
public class PlaylistInfoDeserializer implements JsonDeserializer<PlaylistInfo> {
	
	private Gson gson = new Gson();
	
	@Override
	public PlaylistInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		PlaylistInfo playlistInfo 		= null;
		ArrayList<ChildInfo> entries	= null;
		JsonElement entriesElement		= json.getAsJsonObject().get("entry"),
					commentElement		= json.getAsJsonObject().get("comment");
		int	duration 					= json.getAsJsonObject().get("duration").getAsInt(),
			songCount					= json.getAsJsonObject().get("songCount").getAsInt();
		String created 					= json.getAsJsonObject().get("created").getAsString(),
			   id 						= json.getAsJsonObject().get("id").getAsString(),
			   name 					= json.getAsJsonObject().get("name").getAsString(),
			   owner 					= json.getAsJsonObject().get("owner").getAsString(),
			   comment 					= (commentElement != null) ? commentElement.getAsString() : "";
		boolean isPublic 				= json.getAsJsonObject().get("public").getAsBoolean();
		
		if(entriesElement == null)
			entries = new ArrayList<ChildInfo>();
		else if(entriesElement.isJsonArray()) 
			entries = gson.fromJson(entriesElement, new TypeToken<ArrayList<ChildInfo>>(){}.getType());			
		else {
			entries 				= new ArrayList<ChildInfo>();
			ChildInfo folderInfo	= gson.fromJson(entriesElement, ChildInfo.class);
			entries.add(folderInfo);
		}
		
		playlistInfo = new PlaylistInfo();
		playlistInfo.setComment(comment)
					.setCreationDate(created)
					.setDuration(duration)
					.setEntriesArray(entries)
					.setId(id)
					.setName(name)
					.setOwner(owner)
					.setPublic(isPublic)
					.setSongCount(songCount);
		return playlistInfo;
	}

}
