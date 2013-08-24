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

package net.subclient.subsonic.mappings;

/**
 * 
 * @author Alejandro Celaya Alastrué
 */
public class PodcastInfo extends ChildInfo {
	
	/** Stream ID of this PodcastInfo */
	private String streamId;
	/** Status of this PodcastInfo */
	private String status;
	/** Description of this PodcastInfo */
	private String description;
	/** Publish date of this PodcastInfo */
	private String publishDate;
	
	/**
	 * Constructs a new ChildInfo object with default values
	 * <pre>
	 * 		{
	 * 			id : "-1",
	 * 			title : " - ",
	 * 			album : " - ",
	 * 			albumId : "-1",
	 * 			artist : " - ",
	 * 			artistId : "-1",
	 * 			parent : "-1",
	 * 			coverArt : "-1",
	 * 			isDir : false,
	 * 			isVideo : false,
	 * 			track : 0,
	 * 			size : 0,
	 * 			duration : 0,
	 * 			bitRate : 0,
	 * 			path : "",
	 * 			created : "",
	 * 			year : 0,
	 * 			genre : "",
	 * 			contentType : "",
	 * 			type : "",
	 * 			sufix : "",
	 * 			averageRating : 0,
	 * 			userRating : 0,
	 * 			streamId : "-1",
	 * 			status : "completed",
	 * 			description : "",
	 * 			publishDate : "1970-01-01 00:00:00.0"
	 * 		}
	 * </pre>
	 */
	public PodcastInfo() {
		super();
		this.streamId 		= "-1";
		this.status 		= "completed";
		this.description	= "";
		this.publishDate 	= "1970-01-01 00:00:00.0";
	}
	
	public String getStreamId() {
		return this.streamId;
	}
	public String getStatus() {
		return this.status;
	}
	public String getDescription() {
		return this.description;
	}
	public String getPublishDate() {
		return this.publishDate;
	}
	
	public PodcastInfo setStreamId(String streamId) {
		this.streamId = streamId;
		return this;
	}
	public PodcastInfo setStatus(String status) {
		this.status = status;
		return this;
	}
	public PodcastInfo setDescription(String description) {
		this.description = description;
		return this;
	}
	public PodcastInfo setPublishDate(String publishDate) {
		this.publishDate = publishDate;
		return this;
	}
	
	/**
	 * Returns a new ChildInfo with the same data of this PodcastInfo
	 * @return A new ChildInfo with the same data of this PodcastInfo
	 */
	public ChildInfo toChildInfo() {
		ChildInfo info = new ChildInfo();
		info.setAlbum(this.getAlbum())
			.setAlbumId(this.getAlbumId())
			.setArtist(this.getArtist())
			.setArtistId(this.getArtistId())
			.setBitRate(this.getBitRate())
			.setContentType(this.getContentType())
			.setCoverArt(this.getCoverArt())
			.setCreationDate(this.getCreationDate())
			.setDuration(this.getDuration())
			.setGenre(this.getGenre())
			.setId(this.getId())
			.setIsDir(this.isDir())
			.setIsVideo(this.isVideo())
			.setParent(this.getParent())
			.setPath(this.getPath())
			.setSize(this.getSize())
			.setSuffix(this.getSuffix())
			.setTitle(this.getTitle())
			.setTrack(this.getTrack())
			.setType(this.getType())
			.setYear(this.getYear())
			.setAverageRating(this.getAverageRating())
			.setUserRating(this.getUserRating());
		return info;
	}
	
	/**
	 * The string representation of this podcast is its title
	 * @return The string representation of this podcast
	 */
	@Override
	public String toString() {
		return this.getTitle();
	}
	@Override
	public PodcastInfo clone() {
		PodcastInfo info = new PodcastInfo();
		info.setStreamId(this.streamId)
			.setStatus(this.status)
			.setDescription(this.description)
			.setPublishDate(this.publishDate)
			.setAlbum(this.getAlbum())
			.setAlbumId(this.getAlbumId())
			.setArtist(this.getArtist())
			.setArtistId(this.getArtistId())
			.setBitRate(this.getBitRate())
			.setContentType(this.getContentType())
			.setCoverArt(this.getCoverArt())
			.setCreationDate(this.getCreationDate())
			.setDuration(this.getDuration())
			.setGenre(this.getGenre())
			.setId(this.getId())
			.setIsDir(this.isDir())
			.setIsVideo(this.isVideo())
			.setParent(this.getParent())
			.setPath(this.getPath())
			.setSize(this.getSize())
			.setSuffix(this.getSuffix())
			.setTitle(this.getTitle())
			.setTrack(this.getTrack())
			.setType(this.getType())
			.setYear(this.getYear())
			.setAverageRating(this.getAverageRating())
			.setUserRating(this.getUserRating());
		return info;
	}
	
}
