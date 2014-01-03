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

import net.subclient.subsonic.mappings.Bookmark;
import net.subclient.subsonic.responses.GetBookmarksResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * This class specifies how to deserialize a {@link net.subclient.subsonic.responses.GetBookmarksResponse GetBookmarksResponse} object.
 * It happens that the response could contain an object with name "bookmarks" that is a JSON Object when there is only one child and an empty String when it has no childs.
 * <ul>
 * 		<li>
 * 			This is an example of a bookmarks object with multiple childs.
 *		</li>
 * 			<pre>
 * 				{
 * 					bookmarks : {
 * 						//CHILDS
 * 					}
 * 				}
 * 			</pre>
 *		<li>
 *			This is an example of a bookmarks object with just one child
 * 			<pre>
 * 				{
 * 					bookmarks : ""
 * 				}
 * 			</pre>
 *		</li>
 * </ul>
 * This class takes the empty string, creates an object and returns it.
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class GetBookmarksResponseDeserializer implements JsonDeserializer<GetBookmarksResponse> {
	
	private Gson gson = null;
	
	public GetBookmarksResponseDeserializer() {
		this.gson = new GsonBuilder().registerTypeHierarchyAdapter(Bookmark.class, new BookmarkDeserializer()).create();
	}
	
	@Override
	public GetBookmarksResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		GetBookmarksResponse response	= null;
		Bookmark bookmark				= null;
		JsonElement bookmarksElement	= json.getAsJsonObject().get("bookmarks");
		
		if (bookmarksElement.isJsonObject()) {
			bookmark = this.gson.fromJson(bookmarksElement, Bookmark.class);			
		} else
			bookmark = new Bookmark();
		
		response = new GetBookmarksResponse();
		response.setBookmarks(bookmark);
		return response;
	}

}
