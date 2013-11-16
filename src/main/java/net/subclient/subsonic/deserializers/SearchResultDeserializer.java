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
import java.util.List;

import net.subclient.subsonic.mappings.ChildInfo;
import net.subclient.subsonic.mappings.FolderInfo;
import net.subclient.subsonic.mappings.SearchResult;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * This class specifies how to deserialize a {@link net.subclient.subsonic.mappings.Podcasts Podcasts} object.
 * It happens that the response could contain three objects with names "song", "album" and "album" that are a JSON Array when they have more than one child, but they are a JSON Object when there is only one child.
 * <ul>
 * 		<li>
 * 			This is an example of the object with multiple childs.
 *		</li>
 * 			<pre>
 * 				{
 * 					<i>object</i> : [
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
 *			This is an example of the object object with just one child
 * 			<pre>
 * 				{
 * 					<i>object</i> : {
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
public class SearchResultDeserializer implements JsonDeserializer<SearchResult> {
	
	private Gson gson = new Gson();
	
	@Override
	public SearchResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		SearchResult searchResult		= null;
		List<ChildInfo> songs		= null;
		List<ChildInfo> albums		= null;
		List<FolderInfo> artists	= null;
		JsonElement songsElement		= json.getAsJsonObject().get("song");
		JsonElement albumsElement		= json.getAsJsonObject().get("album");
		JsonElement artistsElement		= json.getAsJsonObject().get("artist");
			
		if(songsElement == null)
			songs = new ArrayList<ChildInfo>();
		else if(songsElement.isJsonArray()) 
			songs = gson.fromJson(songsElement, new TypeToken<List<ChildInfo>>(){}.getType());			
		else {
			songs 			= new ArrayList<ChildInfo>();
			ChildInfo info	= gson.fromJson(songsElement, ChildInfo.class);
			songs.add(info);
		}
		
		if(albumsElement == null)
			albums = new ArrayList<ChildInfo>();
		else if(albumsElement.isJsonArray()) 
			albums = gson.fromJson(albumsElement, new TypeToken<List<ChildInfo>>(){}.getType());			
		else {
			albums 			= new ArrayList<ChildInfo>();
			ChildInfo info	= gson.fromJson(albumsElement, ChildInfo.class);
			albums.add(info);
		}
		
		if(artistsElement == null)
			artists = new ArrayList<FolderInfo>();
		else if(artistsElement.isJsonArray()) 
			artists = gson.fromJson(artistsElement, new TypeToken<List<FolderInfo>>(){}.getType());			
		else {
			artists 				= new ArrayList<FolderInfo>();
			FolderInfo folderInfo	= gson.fromJson(artistsElement, FolderInfo.class);
			artists.add(folderInfo);
		}		
		
		searchResult = new SearchResult();
		searchResult.setSongsArray(songs)
					.setAlbumsArray(albums)
					.setArtistsArray(artists);
		return searchResult;
	}

}
