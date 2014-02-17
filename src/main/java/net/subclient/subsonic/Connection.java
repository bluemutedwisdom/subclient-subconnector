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

package net.subclient.subsonic;

import net.subclient.subsonic.api.BookmarksAPI;
import net.subclient.subsonic.api.BrowseAPI;
import net.subclient.subsonic.api.MediaAnnotationAPI;
import net.subclient.subsonic.api.MediaListAPI;
import net.subclient.subsonic.api.MediaRetrievalAPI;
import net.subclient.subsonic.api.PlaylistsAPI;
import net.subclient.subsonic.api.PodcastsAPI;
import net.subclient.subsonic.api.SearchAPI;
import net.subclient.subsonic.api.SystemAPI;

/**
 * Connection interface defining all the methods needed in the Subsonic connection
 * @see net.subclient.subsonic.api.SystemAPI
 * @see net.subclient.subsonic.api.BrowseAPI
 * @see net.subclient.subsonic.api.MediaListAPI
 * @see net.subclient.subsonic.api.SearchAPI
 * @see net.subclient.subsonic.api.PlaylistsAPI
 * @see net.subclient.subsonic.api.PodcastsAPI
 * @see net.subclient.subsonic.api.MediaRetrievalAPI
 * @see net.subclient.subsonic.api.MediaAnnotationAPI
 * @see net.subclient.subsonic.api.BookmarksAPI
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public interface Connection extends SystemAPI, 
									BrowseAPI, 
									MediaListAPI, 
									SearchAPI, 
									PlaylistsAPI, 
									PodcastsAPI, 
									MediaRetrievalAPI, 
									MediaAnnotationAPI,
									BookmarksAPI {
	
	/*
	 * This interface joins all API interfaces. Take a look at their APIs for documentation
	 */
	
}
