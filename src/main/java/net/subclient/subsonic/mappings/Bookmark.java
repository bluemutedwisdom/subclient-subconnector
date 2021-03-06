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

package net.subclient.subsonic.mappings;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class Bookmark {
	
	@SerializedName("bookmark")
	public List<BookmarkInfo> bookmarks;
	
	public Bookmark() {
		this.bookmarks = new ArrayList<BookmarkInfo>();
	}
	
	public List<BookmarkInfo> getBookmarksArray() {
		return this.bookmarks;
	}
	
	public Bookmark setBookmarksArray(List<BookmarkInfo> bookmarks) {
		this.bookmarks = bookmarks;
		return this;
	}
	
	public Bookmark addBookmark(BookmarkInfo bookmark) {
		this.bookmarks.add(bookmark);
		return this;
	}
	
}
