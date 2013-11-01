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

package net.subclient.subsonic.factories;

import net.subclient.subsonic.deserializers.AlbumsDeserializer;
import net.subclient.subsonic.deserializers.ChannelInfoDeserializer;
import net.subclient.subsonic.deserializers.DirectoryDeserializer;
import net.subclient.subsonic.deserializers.GetAlbumsResponseDeserializer;
import net.subclient.subsonic.deserializers.GetMusicFoldersResponseDeserializer;
import net.subclient.subsonic.deserializers.GetPlaylistsResponseDeserializer;
import net.subclient.subsonic.deserializers.GetPodcastsResponseDeserializer;
import net.subclient.subsonic.deserializers.GetRandomSongsResponseDeserializer;
import net.subclient.subsonic.deserializers.IndexInfoDeserializer;
import net.subclient.subsonic.deserializers.IndexesDeserializer;
import net.subclient.subsonic.deserializers.MusicFoldersDeserializer;
import net.subclient.subsonic.deserializers.PlaylistInfoDeserializer;
import net.subclient.subsonic.deserializers.PlaylistsDeserializer;
import net.subclient.subsonic.deserializers.PodcastsDeserializer;
import net.subclient.subsonic.deserializers.RandomSongsDeserializer;
import net.subclient.subsonic.deserializers.SearchResponseDeserializer;
import net.subclient.subsonic.deserializers.SearchResultDeserializer;
import net.subclient.subsonic.mappings.Albums;
import net.subclient.subsonic.mappings.ChannelInfo;
import net.subclient.subsonic.mappings.Directory;
import net.subclient.subsonic.mappings.IndexInfo;
import net.subclient.subsonic.mappings.Indexes;
import net.subclient.subsonic.mappings.MusicFolders;
import net.subclient.subsonic.mappings.PlaylistInfo;
import net.subclient.subsonic.mappings.Playlists;
import net.subclient.subsonic.mappings.Podcasts;
import net.subclient.subsonic.mappings.RandomSongs;
import net.subclient.subsonic.mappings.SearchResult;
import net.subclient.subsonic.responses.GetAlbumsResponse;
import net.subclient.subsonic.responses.GetMusicFoldersResponse;
import net.subclient.subsonic.responses.GetPlaylistsResponse;
import net.subclient.subsonic.responses.GetPodcastsResponse;
import net.subclient.subsonic.responses.GetRandomSongsResponse;
import net.subclient.subsonic.responses.SearchResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Includes static factory methods to create Gson objects
 * @author Alejandro Celaya Alastrué
 *
 */
public class GsonFactory {
	
	/**
	 * Returns a JSON deserializer that will properly work with Subsonic connections, by including all needed deserialization strategies
	 * @return Gson deserializer
	 */
	public static Gson createDeserializer() {
		return getBuilder().create();
	}
	
	/**
	 * Returns a JSON deserializer that will properly work with Subsonic connections, by including all needed deserialization strategies.
	 * It pretty prints any serialized object
	 * @return Gson deserializer
	 */
	public static Gson createPrettyPrintingDeserializer() {
		return getBuilder().setPrettyPrinting()
						   .create();
	}
	
	/**
	 * Constructs a GsonBuilder object that includes all deserialization strategies
	 * @return
	 */
	private static GsonBuilder getBuilder() {
		return new GsonBuilder().registerTypeHierarchyAdapter(GetMusicFoldersResponse.class	, new GetMusicFoldersResponseDeserializer())
								.registerTypeHierarchyAdapter(MusicFolders.class			, new MusicFoldersDeserializer())
								.registerTypeHierarchyAdapter(IndexInfo.class				, new IndexInfoDeserializer())
								.registerTypeHierarchyAdapter(SearchResponse.class			, new SearchResponseDeserializer())
								.registerTypeHierarchyAdapter(SearchResult.class			, new SearchResultDeserializer())
								.registerTypeHierarchyAdapter(GetPlaylistsResponse.class	, new GetPlaylistsResponseDeserializer())
								.registerTypeHierarchyAdapter(Playlists.class				, new PlaylistsDeserializer())
								.registerTypeHierarchyAdapter(PlaylistInfo.class			, new PlaylistInfoDeserializer())
								.registerTypeHierarchyAdapter(GetAlbumsResponse.class		, new GetAlbumsResponseDeserializer())
								.registerTypeHierarchyAdapter(Albums.class					, new AlbumsDeserializer())
								.registerTypeHierarchyAdapter(GetPodcastsResponse.class		, new GetPodcastsResponseDeserializer())
								.registerTypeHierarchyAdapter(Podcasts.class				, new PodcastsDeserializer())
								.registerTypeHierarchyAdapter(ChannelInfo.class				, new ChannelInfoDeserializer())
								.registerTypeHierarchyAdapter(Directory.class				, new DirectoryDeserializer())
								.registerTypeHierarchyAdapter(Indexes.class					, new IndexesDeserializer())
								.registerTypeHierarchyAdapter(GetRandomSongsResponse.class	, new GetRandomSongsResponseDeserializer())
								.registerTypeHierarchyAdapter(RandomSongs.class				, new RandomSongsDeserializer());
	}
	
}
