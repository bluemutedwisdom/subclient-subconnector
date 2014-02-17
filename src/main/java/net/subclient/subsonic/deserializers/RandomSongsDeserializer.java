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

import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.mappings.RandomSongs;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * This class specifies how to deserialize an {@link net.subclient.subsonic.mappings.RandomSongs RandomSongs} object.
 * It happens that the response could contain an object with name "song" that is a JSON Array when it has more than one child, but it is a JSON Object when there is only one child.
 * <ul>
 * 		<li>
 * 			This is an example of a song object with multiple childs.
 *		</li>
 * 			<pre>
 * 				{
 * 					song : [
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
 *			This is an example of a song object with just one child
 * 			<pre>
 * 				{
 * 					song : {
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
public class RandomSongsDeserializer implements JsonDeserializer<RandomSongs> {
	
	/**
	 * Gson object used to deserialize content
	 */
	private Gson gson = new Gson();
	
	@Override
	public RandomSongs deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		RandomSongs randomSongs 				= null;
		List<ChildInfo> randomSongsArray	= null;
		JsonElement randomSongsElement			= json.getAsJsonObject().get("song");
		
		if(randomSongsElement.isJsonArray()) 
			randomSongsArray = gson.fromJson(randomSongsElement, new TypeToken<List<ChildInfo>>(){}.getType());			
		else {
			randomSongsArray = new ArrayList<ChildInfo>();
			ChildInfo childInfo = gson.fromJson(randomSongsElement, ChildInfo.class);
			randomSongsArray.add(childInfo);
		}
		
		randomSongs = new RandomSongs();
		randomSongs.setRandomSongsArray(randomSongsArray);
		return randomSongs;
	}

}
