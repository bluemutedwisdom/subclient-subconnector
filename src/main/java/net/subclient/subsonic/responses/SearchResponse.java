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

package net.subclient.subsonic.responses;

import com.google.gson.annotations.SerializedName;

import net.subclient.subsonic.mappings.SearchResult;

/**
 * Object returned on {@link net.subclient.subsonic.SubsonicConnection#search(String) search} calls
 * @author Alejandro Celaya Alastrué
 * @see net.subclient.subsonic.SubsonicConnection
 */
public class SearchResponse extends SubsonicResponse {
	
	/** SearchResult wrapper object */
	@SerializedName("searchResult2")
	private SearchResult searchResult = null;
	
	/**
	 * Constructs a new SearchResponse with a default constructed SearchResponse object
	 */
	public SearchResponse() {
		super();
		this.searchResult = new SearchResult();
	}
	
	public SearchResult getSearchResults() {
		return this.searchResult;
	}
	public SearchResponse setSearchResult(SearchResult searchResults) {
		this.searchResult = searchResults;
		return this;
	}
	
}
