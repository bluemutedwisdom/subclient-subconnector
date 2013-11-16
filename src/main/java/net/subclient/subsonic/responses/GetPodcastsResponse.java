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

import net.subclient.subsonic.mappings.Podcasts;

/**
 * Object returned on {@link net.subclient.subsonic.Connection#getPodcasts() getPodcasts} calls
 * @see net.subclient.subsonic.Connection
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class GetPodcastsResponse extends SubsonicResponse {
	
	/** Podcasts wrapper object */
	private Podcasts podcasts;
	
	/**
	 * Constructs a new GetPodcastsResponse with a default constructed Podcasts object
	 */
	public GetPodcastsResponse() {
		super();
		this.podcasts = new Podcasts();
	}
	
	public Podcasts getPodcasts() {
		return this.podcasts;
	}
	
	public GetPodcastsResponse setPodcasts(Podcasts podcasts) {
		this.podcasts = podcasts;
		return this;
	}
	
}
