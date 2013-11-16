package net.subclient.subsonic.util;


/**
 * Valid types for {@link net.subclient.subsonic.Connection#getAlbumsList(GetAlbumsType) getAlbumsList} method in {@link net.subclient.subsonic.Connection Connection} class
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public enum GetAlbumsType {
	
	RANDOM		("random"	, new Version(1)),
	NEWEST		("newest"	, new Version(1)),
	HIGHEST		("highest"	, new Version(1)),
	FREQUENT	("frequent"	, new Version(1)),
	RECENT		("recent"	, new Version(1));
	
	/** Parameter value to be sent to Subsonic server */
	private String type;
	/** Minimum Server version needed for this type to work */
	private Version minVersion;
	
	private GetAlbumsType(String type, Version minVersion) {
		this.type 		= type;
		this.minVersion	= minVersion;
	}
	
	public Version getMinVersion() {
		return this.minVersion;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	
}
