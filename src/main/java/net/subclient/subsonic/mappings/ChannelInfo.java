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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Java representation of the "channel" JSON object.
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class ChannelInfo extends FolderInfo implements Cloneable {
	
	/** Title of this channel */
	private String title;
	/** Status of this channel */
	private String status;
	/** Description of this channel */
	private String description;
	/** URL where the episodes are downloaded from */
	private String url;
	/** Publish date of the newest episode of this chanel */
	private String publishDate;
	/** Array of episodes availables on this channel */
	@SerializedName("episode")
	private List<PodcastInfo> episodesArray;
	
	/**
	 * Constructs a new ChannelInfo object with default values:
	 * <pre>
	 * 		{
	 * 			id : "-1",
	 * 			title : "-",
	 * 			status : "completed",
	 * 			description : "",
	 * 			url : "http://www.subclient.net",
	 * 			episode : []
	 * 		}
	 * </pre>
	 */
	public ChannelInfo() {
		super();
		this.title 			= " - ";
		this.status 		= "completed";
		this.description	= "";
		this.url 			= "http://www.subclient.net";
		this.publishDate	= "";
		this.episodesArray 	= new ArrayList<PodcastInfo>();
	}
	
	/**
	 * Returns this channel title
	 * @return this channel title
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * Returns this channel status
	 * @return this channel status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * Returns this channel description
	 * @return this channel description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * Returns this channel url as java.net.URL object.
	 * If a MalformedURLException occurs, null is returned
	 * @return this channel URL
	 */
	public URL getUrl() {
		URL urlObj = null;
		try {
			urlObj = new URL(this.url);
		} catch(MalformedURLException e) {
			urlObj = null;
		}
		return urlObj;
	}
	/**
	 * Returns this channel publish date, wich is its newest episode publish date
	 * @return This channel publish date
	 */
	public String getPublishDate() {
		//If the publish date has not been set, get the newest episode publish date
		if(this.publishDate.equalsIgnoreCase("") && this.episodesArray.size() > 0) {
			this.publishDate = this.episodesArray.get(0).getPublishDate();
		}
		return this.publishDate;
	}
	/**
	 * Returns the array of episodes of this channel
	 * @return The array of episodes of this channel
	 */
	public List<PodcastInfo> getEpisodesArray() {
		return this.episodesArray;
	}
	
	/**
	 * Sets this channel title
	 * @param title New title
	 * @return this channel
	 */
	public ChannelInfo setTitle(String title) {
		this.title = title;
		return this;
	}
	/**
	 * Sets this channel status
	 * @param status New status
	 * @return this channel
	 */
	public ChannelInfo setStatus(String status) {
		this.status = status;
		return this;
	}
	/**
	 * Sets this channel description
	 * @param description New description
	 * @return this channel
	 */
	public ChannelInfo setDescription(String description) {
		this.description = description;
		return this;
	}
	/**
	 * Sets this channel url as java.net.URL object
	 * @param urlObj New url
	 * @return this channel
	 */
	public ChannelInfo setUrl(URL urlObj) {
		this.url = urlObj.toString();
		return this;
	}
	/**
	 * Sets this channel publish date
	 * @param publishDate New publish date
	 * @return This channel
	 */
	public ChannelInfo setPublishDate(String publishDate) {
		this.publishDate = publishDate;
		return this;
	}
	/**
	 * Sets this channel episodes array
	 * @param episodesArray New episodes array
	 * @return this channel
	 */
	public ChannelInfo setEpisodesArray(List<PodcastInfo> episodesArray) {
		this.episodesArray = episodesArray;
		return this;
	}
	/**
	 * Adds an episode to this channel episodes array
	 * @param episode New episode to add
	 * @return this channel
	 */
	public ChannelInfo addEpisode(PodcastInfo episode) {
		this.episodesArray.add(episode);
		return this;
	}
	
	/**
	 * The string representation of this channel is its title
	 * @return Title of this channel as its String representation
	 */
	@Override
	public String toString() {
		return this.title;
	}
	@Override
	public ChannelInfo clone() {
		ChannelInfo info = new ChannelInfo();
		info.setDescription(this.getDescription())
			.setEpisodesArray(this.getEpisodesArray())
			.setStatus(this.getStatus())
			.setTitle(this.getTitle())
			.setUrl(this.getUrl())
			.setId(this.getId());
		return info;
	}
	
}
