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

package net.subclient.subsonic;

/**
 * List of Subsonic API methods supported by Subconnector
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public enum ApiMethods {
	
	PING				(String.format(ApiMethods.REST_PREFIX, "ping.view")),
	GET_LICENSE			(String.format(ApiMethods.REST_PREFIX, "getLicense.view")),
	GET_MUSIC_FOLDERS	(String.format(ApiMethods.REST_PREFIX, "getMusicFolders.view")),
	GET_INDEXES			(String.format(ApiMethods.REST_PREFIX, "getIndexes.view")),
	GET_MUSIC_DIRECTORY	(String.format(ApiMethods.REST_PREFIX, "getMusicDirectory.view")),
	SEARCH_2			(String.format(ApiMethods.REST_PREFIX, "search2.view")),
	GET_PLAYLISTS		(String.format(ApiMethods.REST_PREFIX, "getPlaylists.view")),
	GET_PLAYLIST		(String.format(ApiMethods.REST_PREFIX, "getPlaylist.view")),
	CREATE_PLAYLIST		(String.format(ApiMethods.REST_PREFIX, "createPlaylist.view")),
	DELETE_PLAYLIST		(String.format(ApiMethods.REST_PREFIX, "deletePlaylist.view")),
	DOWNLOAD			(String.format(ApiMethods.REST_PREFIX, "download.view")),
	STREAM				(String.format(ApiMethods.REST_PREFIX, "stream.view")),
	GET_COVER_ART		(String.format(ApiMethods.REST_PREFIX, "getCoverArt.view")),
	GET_ALBUM_LIST		(String.format(ApiMethods.REST_PREFIX, "getAlbumList.view")),
	GET_RANDOM_SONGS	(String.format(ApiMethods.REST_PREFIX, "getRandomSongs.view")),
	GET_PODCASTS		(String.format(ApiMethods.REST_PREFIX, "getPodcasts.view")),
	REFRESH_PODCASTS	(String.format(ApiMethods.REST_PREFIX, "refreshPodcasts.view")),
	CREATE_PODCAST		(String.format(ApiMethods.REST_PREFIX, "createPodcastChannel.view")),
	DELETE_PODCAST		(String.format(ApiMethods.REST_PREFIX, "deletePodcastChannel.view")),
	SET_RATING			(String.format(ApiMethods.REST_PREFIX, "setRating.view")),
	STAR				(String.format(ApiMethods.REST_PREFIX, "star.view")),
	UNSTAR				(String.format(ApiMethods.REST_PREFIX, "unstar.view")),
	GET_STARRED			(String.format(ApiMethods.REST_PREFIX, "getStarred.view"));
	
	private static final String REST_PREFIX	= "/rest/%s";
	
	private String path;
	
	private ApiMethods(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return this.path;
	}
	
}
