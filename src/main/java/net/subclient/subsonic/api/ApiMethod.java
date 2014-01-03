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

package net.subclient.subsonic.api;

import net.subclient.subsonic.util.Version;

/**
 * List of Subsonic API methods supported by Subconnector
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public enum ApiMethod {
	
	PING				(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "ping.view")),
	GET_LICENSE			(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "getLicense.view")),
	GET_MUSIC_FOLDERS	(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "getMusicFolders.view")),
	GET_INDEXES			(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "getIndexes.view")),
	GET_MUSIC_DIRECTORY	(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "getMusicDirectory.view")),
	SEARCH_2			(new Version(1, 4, 0), String.format(ApiMethod.REST_PREFIX, "search2.view")),
	GET_PLAYLISTS		(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "getPlaylists.view")),
	GET_PLAYLIST		(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "getPlaylist.view")),
	CREATE_PLAYLIST		(new Version(1, 2, 0), String.format(ApiMethod.REST_PREFIX, "createPlaylist.view")),
	DELETE_PLAYLIST		(new Version(1, 2, 0), String.format(ApiMethod.REST_PREFIX, "deletePlaylist.view")),
	DOWNLOAD			(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "download.view")),
	STREAM				(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "stream.view")),
	GET_COVER_ART		(new Version(1, 0, 0), String.format(ApiMethod.REST_PREFIX, "getCoverArt.view")),
	GET_ALBUM_LIST		(new Version(1, 2, 0), String.format(ApiMethod.REST_PREFIX, "getAlbumList.view")),
	GET_RANDOM_SONGS	(new Version(1, 2, 0), String.format(ApiMethod.REST_PREFIX, "getRandomSongs.view")),
	GET_PODCASTS		(new Version(1, 6, 0), String.format(ApiMethod.REST_PREFIX, "getPodcasts.view")),
	REFRESH_PODCASTS	(new Version(1, 9, 0), String.format(ApiMethod.REST_PREFIX, "refreshPodcasts.view")),
	CREATE_PODCAST		(new Version(1, 9, 0), String.format(ApiMethod.REST_PREFIX, "createPodcastChannel.view")),
	DELETE_PODCAST		(new Version(1, 9, 0), String.format(ApiMethod.REST_PREFIX, "deletePodcastChannel.view")),
	SET_RATING			(new Version(1, 6, 0), String.format(ApiMethod.REST_PREFIX, "setRating.view")),
	STAR				(new Version(1, 8, 0), String.format(ApiMethod.REST_PREFIX, "star.view")),
	UNSTAR				(new Version(1, 8, 0), String.format(ApiMethod.REST_PREFIX, "unstar.view")),
	GET_STARRED			(new Version(1, 8, 0), String.format(ApiMethod.REST_PREFIX, "getStarred.view")),
	GET_BOOKMARKS		(new Version(1, 9, 0), String.format(ApiMethod.REST_PREFIX, "getBookmarks.view")),
	CREATE_BOOKMARK		(new Version(1, 9, 0), String.format(ApiMethod.REST_PREFIX, "createBookmark.view")),
	DELETE_BOOKMARK		(new Version(1, 9, 0), String.format(ApiMethod.REST_PREFIX, "deleteBookmark.view")),
	SCROBBLE			(new Version(1, 5, 0), String.format(ApiMethod.REST_PREFIX, "scrobble.view"));
	
	private static final String REST_PREFIX	= "/rest/%s";
	
	private String path;
	private Version version;
	
	private ApiMethod(Version version, String path) {
		this.version	= version;
		this.path 		= path;
	}
	
	@Override
	public String toString() {
		return this.path;
	}
	
	public Version getVersion() {
		return this.version;
	}
	
}
