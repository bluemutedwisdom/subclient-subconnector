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
 * Java representation of a child JSON object
 * @author Alejandro Celaya Alastrué
 */
public class ChildInfo implements Cloneable {
	
	/** ID of this child */
	private String id;
	/** Title of this child */
	private String title;
	/** Album of this child */
	private String album;
	/** ID of the album of this child */
	private String albumId;
	/** Artist of this child */
	private String artist;
	/** ID of the artist of this child */
	private String artistId;
	/** ID of the parent element of this child */
	private String parent;
	/** ID of the cover art of this child */
	private String coverArt;
	/** Defines if this child is a directory */
	private boolean isDir;
	/** Defines if this child is video */
	private boolean isVideo;
	/** Track number of this child */
	private int track;
	/** Size of this child in bytes */
	private long size;
	/** Duration of this child in seconds */
	private int duration;
	/** Bitrate of this child in Kbs */
	private int bitRate;
	/** Path of this child */
	private String path;
	/** Date on wich this child was created */
	private String created;
	/** Year of this child */
	private int year;
	/** Genre of this child */
	private String genre;
	/** Content-type of this child */
	private String contentType;
	/** Type of this child */
	private String type;
	/** Suffix of this child */
	private String suffix;
	/** Average rating of this child */
	private float averageRating;
	/** Current user rating of this child */
	private int userRating;
	
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
	 * 			userRating : 0
	 * 		}
	 * </pre>
	 */
	public ChildInfo() {
		this.id 			= "-1";
		this.title 			= " - ";
		this.album 			= " - ";
		this.albumId 		= "-1";
		this.artist 		= " - ";
		this.artistId 		= "-1";
		this.parent 		= "-1";
		this.coverArt 		= "-1";
		this.isDir 			= false;
		this.isVideo 		= false;
		this.track 			= 0;
		this.size 			= 0;
		this.duration 		= 0;
		this.bitRate 		= 0;
		this.path 			= "";
		this.created 		= "";
		this.year 			= 0;
		this.genre 			= "";
		this.contentType	= "";
		this.type 			= "";
		this.suffix 		= "";
		this.averageRating	= 0;
		this.userRating		= 0;
	}
	
	/**
	 * Returns this child ID
	 * @return This child ID
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * Returns this child title
	 * @return This child title
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * Returns this child album name
	 * @return This child album name
	 */
	public String getAlbum() {
		return this.album;
	}
	/**
	 * Returns this child album ID 
	 * @return This child album ID
	 */
	public String getAlbumId() {
		return this.albumId;
	}
	/**
	 * Returns this child artist name
	 * @return This child artist name
	 */
	public String getArtist() {
		return this.artist;
	}
	/**
	 * Returns this child artist ID
	 * @return This child artist ID
	 */
	public String getArtistId() {
		return this.artistId;
	}
	/**
	 * Returns this child parent ID
	 * @return This child parent ID
	 */
	public String getParent() {
		return this.parent;
	}
	/**
	 * Returns this child cover art id
	 * @return This child cover art id
	 */
	public String getCoverArt() {
		return this.coverArt;
	}
	/**
	 * Defines if this child is a directory
	 * @return True if this child is a directory. False otherwise
	 */
	public boolean isDir() {
		return this.isDir;
	}
	/**
	 * Defines if this child is a video
	 * @return True if this child is a video. False otherwise
	 */
	public boolean isVideo() {
		return this.isVideo;
	}
	/**
	 * Returns this child track number
	 * @return This child track number
	 */
	public int getTrack() {
		return this.track;
	}
	/**
	 * Returns this child size in bytes
	 * @return This child size in bytes
	 */
	public long getSize() {
		return this.size;
	}
	/**
	 * Returns this child duration in seconds
	 * @return This child duration in seconds
	 */
	public int getDuration() {
		return this.duration;
	}
	/**
	 * Returns this child bitrate in Kbs
	 * @return This child bitrate in Kbs
	 */
	public int getBitRate() {
		return this.bitRate;
	}
	/**
	 * Returns this child path
	 * @return This child path
	 */
	public String getPath() {
		return this.path;
	}
	/**
	 * Returns this child creation date
	 * @return This child creation date
	 */
	public String getCreationDate() {
		return this.created;
	}
	/**
	 * Returns this child year
	 * @return This child year
	 */
	public int getYear() {
		return this.year;
	}
	/**
	 * Returns this child genre
	 * @return This child genre
	 */
	public String getGenre() {
		return this.genre;
	}
	/**
	 * Returns this child content-type
	 * @return This child content-type
	 */
	public String getContentType() {
		return this.contentType;
	}
	/**
	 * Returns this child type
	 * @return This child type
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * Returns this child suffix
	 * @return This child suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}
	/**
	 * Returns this child average rating
	 * @return This child average rating
	 */
	public float getAverageRating() {
		return this.averageRating;
	}
	/**
	 * Returns this child current user rating
	 * @return This child current user rating
	 */
	public int getUserRating() {
		return this.userRating;
	}
	
	/**
	 * Sets this child ID
	 * @param id New ID
	 * @return this child
	 */
	public ChildInfo setId(String id) {
		this.id = id;
		return this;
	}
	/**
	 * Sets this child title
	 * @param title New title
	 * @return this child
	 */
	public ChildInfo setTitle(String title) {
		this.title = title;
		return this;
	}
	/**
	 * Sets this child album name
	 * @param album New album name
	 * @return this child
	 */
	public ChildInfo setAlbum(String album) {
		this.album = album;
		return this;
	}
	/**
	 * Sets this child album ID
	 * @param albumId New album ID
	 * @return this child
	 */
	public ChildInfo setAlbumId(String albumId) {
		this.albumId = albumId;
		return this;
	}
	/**
	 * Sets this child artist name
	 * @param artist New artist name
	 * @return this child
	 */
	public ChildInfo setArtist(String artist) {
		this.artist = artist;
		return this;
	}
	/**
	 * Sets this child artist ID
	 * @param artistId New artist ID
	 * @return this child
	 */
	public ChildInfo setArtistId(String artistId) {
		this.artistId = artistId;
		return this;
	}
	/**
	 * Sets this child parent ID
	 * @param parent New parent id
	 * @return this child
	 */
	public ChildInfo setParent(String parent) {
		this.parent = parent;
		return this;
	}
	/**
	 * Sets this child cover art ID
	 * @param coverArt New cover art ID
	 * @return this child
	 */
	public ChildInfo setCoverArt(String coverArt) {
		this.coverArt = coverArt;
		return this;
	}
	/**
	 * Sets if this child is a directory
	 * @param isDir True to make this child a directory. False otherwise
	 * @return this child
	 */
	public ChildInfo setIsDir(boolean isDir) {
		this.isDir = isDir;
		return this;
	}
	/**
	 * Sets if this child is a video
	 * @param isVideo True to make this child a video. False otherwise
	 * @return this child
	 */
	public ChildInfo setIsVideo(boolean isVideo) {
		this.isVideo = isVideo;
		return this;
	}
	/**
	 * Sets this child track number
	 * @param track New track number
	 * @return this child;
	 */
	public ChildInfo setTrack(int track) {
		this.track = track;
		return this;
	}
	/**
	 * Sets this child size in bytes
	 * @param size New size in bytes
	 * @return this child
	 */
	public ChildInfo setSize(long size) {
		this.size = size;
		return this;
	}
	/**
	 * Sets this child duration in seconds
	 * @param duration New duration in seconds
	 * @return this child
	 */
	public ChildInfo setDuration(int duration) {
		this.duration = duration;
		return this;
	}
	/**
	 * Sets this child bitrate in Kbs
	 * @param bitRate New bitrate in Kbs
	 * @return this child
	 */
	public ChildInfo setBitRate(int bitRate) {
		this.bitRate = bitRate;
		return this;
	}
	/**
	 * Sets this child path
	 * @param path New path
	 * @return this child
	 */
	public ChildInfo setPath(String path) {
		this.path = path;
		return this;
	}
	/**
	 * Sets this child creation date
	 * @param created New creation date
	 * @return this child
	 */
	public ChildInfo setCreationDate(String created) {
		this.created = created;
		return this;
	}
	/**
	 * Sets this child year
	 * @param year New year
	 * @return this child
	 */
	public ChildInfo setYear(int year) {
		this.year = year;
		return this;
	}
	/**
	 * Sets this child genre
	 * @param genre New genre
	 * @return this child
	 */
	public ChildInfo setGenre(String genre) {
		this.genre = genre;
		return this;
	}
	/**
	 * Sets this child content-type
	 * @param contentType New content-type
	 * @return this child
	 */
	public ChildInfo setContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}
	/**
	 * Sets this child type
	 * @param type New type
	 * @return this child
	 */
	public ChildInfo setType(String type) {
		this.type = type;
		return this;
	}
	/**
	 * Sets this child cuffix
	 * @param suffix New suffix
	 * @return this child
	 */
	public ChildInfo setSuffix(String suffix) {
		this.suffix = suffix;
		return this;
	}
	/**
	 * Sets this child average rating
	 * @param averageRating New average rating
	 * @return This child
	 */
	public ChildInfo setAverageRating(float averageRating) {
		this.averageRating = averageRating;
		return this;
	}
	/**
	 * Sets this child current user rating
	 * @param userRating New user rating
	 * @return This child
	 */
	public ChildInfo setUserRating(int userRating) {
		this.userRating = userRating;
		return this;
	}
	
	/**
	 * The string representation of this child is its title
	 * @return Title of this child as its String representation 
	 */
	@Override
	public String toString() {
		return this.title;
	}
	@Override
	public ChildInfo clone() {
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
	
}
