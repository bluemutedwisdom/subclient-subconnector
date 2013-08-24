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

import net.subclient.subsonic.mappings.SearchResult;
import net.subclient.subsonic.responses.SearchResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * This class specifies how to deserialize a {@link net.subclient.subsonic.responses.SearchResponse SearchResponse} object.
 * It happens that the response could contain an object with name "searchResult2" that is a JSON Array when it has more than one child, but it is a JSON Object when there is only one child.
 * <ul>
 * 		<li>
 * 			This is an example of a searchResult2 object with multiple childs.
 *		</li>
 * 			<pre>
 * 				{
 * 					searchResult2 : [
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
 *			This is an example of a searchResult2 object with just one child
 * 			<pre>
 * 				{
 * 					searchResult2 : {
 * 						//CHILD_ONE
 * 					}
 * 				}
 * 			</pre>
 *		</li>
 * </ul>
 * This class takes the single child, creates an array and returns an array with that child in those cases.
 * @author Alejandro Celaya Alastrué
 */
public class SearchResponseDeserializer implements JsonDeserializer<SearchResponse> {
	
	private Gson gson = null;
	
	public SearchResponseDeserializer() {
		this.gson = new GsonBuilder().registerTypeHierarchyAdapter(SearchResult.class, new SearchResultDeserializer()).create();
	}
	
	@Override
	public SearchResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		SearchResponse response 	= null;
		SearchResult searchResult	= null;
		JsonElement resultElement	= json.getAsJsonObject().get("searchResult2");
		String status				= json.getAsJsonObject().get("status").getAsString(),
			   version				= json.getAsJsonObject().get("version").getAsString();
		
		if(resultElement.isJsonObject()) {
			searchResult = this.gson.fromJson(resultElement, SearchResult.class);			
		} else
			searchResult = new SearchResult();
		
		response = new SearchResponse();
		response.setSearchResult(searchResult)
				.setStatus(status)
				.setVersion(version);
		return response;
	}

}
