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

import net.subclient.subsonic.mappings.ChannelInfo;

/**
 * Object returned on {@link net.subclient.subsonic.SubsonicConnection#getPodcastEpisodes(String) getPodcastEpisodes} calls
 * @author Alejandro Celaya Alastrué
 * @see net.subclient.subsonic.SubsonicConnection
 */
public class GetPodcastResponse extends SubsonicResponse {
	
	/** ChannelInfo wrapper object */
	private ChannelInfo channel;
	
	/**
	 * Constructs a new GetPodcastResponse with a default constructed ChannelInfo object
	 */
	public GetPodcastResponse() {
		super();
		this.channel = new ChannelInfo();
	}
	
	public ChannelInfo getChannel() {
		return this.channel;
	}
	
	public GetPodcastResponse setChannel(ChannelInfo channel) {
		this.channel = channel;
		return this;
	}
	
}
