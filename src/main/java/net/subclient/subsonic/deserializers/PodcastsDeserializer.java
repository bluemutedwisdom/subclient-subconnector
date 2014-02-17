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

package net.subclient.subsonic.deserializers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import net.subclient.subsonic.mappings.ChannelInfo;
import net.subclient.subsonic.mappings.Podcasts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * This class specifies how to deserialize a {@link net.subclient.subsonic.mappings.Podcasts Podcasts} object.
 * It happens that the response could contain an object with name "channel" that is a JSON Array when it has more than one child, but it is a JSON Object when there is only one child.
 * <ul>
 * 		<li>
 * 			This is an example of a channel object with multiple childs.
 *		</li>
 * 			<pre>
 * 				{
 * 					channel : [
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
 *			This is an example of a channel object with just one child
 * 			<pre>
 * 				{
 * 					channel : {
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
public class PodcastsDeserializer implements JsonDeserializer<Podcasts> {
	
	private Gson gson = null;
	
	public PodcastsDeserializer() {
		this.gson = new GsonBuilder().registerTypeHierarchyAdapter(ChannelInfo.class, new ChannelInfoDeserializer()).create();
	}
	
	@Override
	public Podcasts deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Podcasts podcasts 				= null;
		List<ChannelInfo> channels	= null;
		JsonElement channelsElement		= json.getAsJsonObject().get("channel");
		
		if(channelsElement.isJsonArray()) 
			channels = gson.fromJson(channelsElement, new TypeToken<List<ChannelInfo>>(){}.getType());			
		else {
			channels 				= new ArrayList<ChannelInfo>();
			ChannelInfo childInfo	= gson.fromJson(channelsElement, ChannelInfo.class);
			channels.add(childInfo);
		}
		
		podcasts = new Podcasts();
		podcasts.setChannelsArray(channels);
		return podcasts;
	}

}
