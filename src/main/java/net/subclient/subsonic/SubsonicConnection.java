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

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.ws.http.HTTPException;

import net.subclient.subsonic.api.ApiMethod;
import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.factories.GsonFactory;
import net.subclient.subsonic.mappings.ChannelInfo;
import net.subclient.subsonic.responses.GetAlbumsResponse;
import net.subclient.subsonic.responses.GetBookmarksResponse;
import net.subclient.subsonic.responses.GetIndexesResponse;
import net.subclient.subsonic.responses.GetLicenseResponse;
import net.subclient.subsonic.responses.GetMusicDirectoryResponse;
import net.subclient.subsonic.responses.GetMusicFoldersResponse;
import net.subclient.subsonic.responses.GetPlaylistResponse;
import net.subclient.subsonic.responses.GetPlaylistsResponse;
import net.subclient.subsonic.responses.GetPodcastResponse;
import net.subclient.subsonic.responses.GetPodcastsResponse;
import net.subclient.subsonic.responses.GetRandomSongsResponse;
import net.subclient.subsonic.responses.GetStarredResponse;
import net.subclient.subsonic.responses.SearchResponse;
import net.subclient.subsonic.responses.SubsonicResponse;
import net.subclient.subsonic.util.AlbumRating;
import net.subclient.subsonic.util.GetAlbumsType;
import net.subclient.subsonic.util.HttpParameter;
import net.subclient.subsonic.util.Version;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Connection interface between a Java application and Subsonic API
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class SubsonicConnection implements Connection {
	
	/** Identifier of the main JSON object in any Subsonic response */
    private static final String SUBSONIC_RESPONSE_IDENTIFIER	= "subsonic-response";
    /** JSON Content-type header */
    private static final String JSON_CONTENT_TYPE 				= "application/json";
    /** Default client identifier value */
    private static final String DEFAULT_CLIENT_IDENTIFIER 		= "Subclient";
    
    /** Subsonic server URL */
    private URL serverURL 				= null;
    /** Parameters string to be sent on each request */
    private String parametersString 	= null;
    /** Defines if the connection is a HTTPS connection */
    private boolean isSSL				= false;
    /** JSON object handler used to parse Subsonic server responses */
    private Gson gson 					= null;
    /** Parser used to get the propper level in the JSON response object */
    private JsonParser parser			= null;
    /** La versión de la API del servidor */
    private Version serverApiVersion	= null;
    
    /**
     * Constructs a SubsonicConnection to the specified server URL using the provided username and password. It is assumed password is hex-encoded
     * @param urlObj Server URL
     * @param user Username
     * @param pass Hex-encoded password
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public SubsonicConnection(URL urlObj, String user, String pass) throws KeyManagementException, NoSuchAlgorithmException {
    	this(urlObj, user, pass, DEFAULT_CLIENT_IDENTIFIER, true);
    }
    /**
     * Constructs a SubsonicConnection to the specified server URL using the provided username and password.
     * @param urlObj Server URL
     * @param user Username
     * @param pass Password, either hex-encoded or not
     * @param isPassEncoded Defines if password is hex-encoded or not
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public SubsonicConnection(URL urlObj, String user, String pass, boolean isPassEncoded) throws KeyManagementException, NoSuchAlgorithmException {
    	this(urlObj, user, pass, DEFAULT_CLIENT_IDENTIFIER, isPassEncoded);
    }
    /**
     * Constructs a SubsonicConnection to the specified server URL using the provided username, password and client identifier. It is assumed password is hex-encoded
     * @param urlObj Server URL
     * @param user Username
     * @param pass Hex-encoded password
     * @param clientIdentifier Client identifier (Identifies this application in Subsonic web interface and logs)
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public SubsonicConnection(URL urlObj, String user, String pass, String clientIdentifier) throws KeyManagementException, NoSuchAlgorithmException {
    	this(urlObj, user, pass, clientIdentifier, true);
    }
    /**
     * Constructs a SubsonicConnection to the specified server URL using the provided username, password and client identifier.
     * @param urlObj Server URL
     * @param user Username
     * @param pass Password, either hex-encoded or not
     * @param clientIdentifier Client identifier (Identifies this application in Subsonic web interface and logs)
     * @param isPassEncoded Defines if password is hex-encoded or not
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public SubsonicConnection(URL urlObj, String user, String pass, String clientIdentifier, boolean isPassEncoded) throws KeyManagementException, NoSuchAlgorithmException {
    	this.serverURL 				= urlObj;
    	HttpParameter userParam 	= new HttpParameter("u", user);
    	HttpParameter passParam 	= new HttpParameter("p", (!isPassEncoded) ? pass : String.format("enc:%s", pass));
    	HttpParameter clientParam	= new HttpParameter("c", clientIdentifier);
    	HttpParameter jsonParam 	= new HttpParameter("f", "json");
    	
    	//Generate parameters string
    	this.parametersString = String.format("%s&%s&%s&%s", userParam.toString(), passParam.toString(), clientParam.toString(), jsonParam.toString());
    	
    	//Define JSON object handlers
    	this.gson	= GsonFactory.createDeserializer();
    	this.parser	= new JsonParser();
        
        //If URL uses HTTPS protocol then SSL is assumed
        this.isSSL = (urlObj.getProtocol().equalsIgnoreCase("https")) ? true : false;
        if (this.isSSL) this.setSSLProperties();
        
        //Initialize API version environement
        Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				initApiVersion();
			}
        });
        thread.start();
    }
    /**
     * Prepares HTTPS connections to accept any domains and self-signed certificates
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    private void setSSLProperties() throws NoSuchAlgorithmException, KeyManagementException {
    	//Disable certificate chacks to force trust self-signed certificates
		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {     
				@Override
				public X509Certificate[] getAcceptedIssuers() { 
					return null;
				} 
				@Override
				public void checkClientTrusted(X509Certificate[] certs, String authType) {}
				@Override
				public void checkServerTrusted(X509Certificate[] certs, String authType) {}
			} 
		};
		SSLContext sc = null;
		sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		
		//Set a hostname verifier that trust any hostname
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}
    
    /**
     * Performs a connection to the server executing specified method with no parameters. It is assumed that JSON must be returned
     * @param method
     * @return The performed HTTP connection InputStream
     * @throws IOException
     * @throws InvalidResponseException
     * @throws HTTPException
     * @throws CompatibilityException 
     */
    private InputStream connect(ApiMethod method) throws IOException, InvalidResponseException, HTTPException, CompatibilityException {
    	return this.connect(method, new ArrayList<HttpParameter>(), true);
    }
    /**
     * Performs a connection to the server executing specified method and passing provided parameters. It is assumed that JSON must be returned
     * @param method One of the supported methods
     * @param parameters Parametters to be passed to server
     * @return The performed HTTP connection InputStream
     * @throws IOException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200
     * @throws CompatibilityException 
     */
    private InputStream connect(ApiMethod method, List<HttpParameter> parameters) throws IOException, InvalidResponseException, HTTPException, CompatibilityException {
    	return this.connect(method, parameters, true);
    }
    /**
     * Performs a connection to the server executing specified method and passing provided parameters.
     * @param method One of the supported methods
     * @param parameters Parametters to be passed to server
     * @param isJson Defines if JSON is expected. It won't be JSON on any method returning binary contents
     * @return The performed HTTP connection InputStream
     * @throws IOException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200
     * @throws CompatibilityException 
     */
    private InputStream connect(ApiMethod method, List<HttpParameter> parameters, boolean isJson) throws IOException, InvalidResponseException, HTTPException, CompatibilityException {
    	// Generate URL object
        URL url = new URL(this.serverURL.toString() + method.toString());
        // Append version param to parameters array
        parameters.add(new HttpParameter("v", this.getVersionCompatible(method.getVersion()).toString(true)));
        
        // Open HTTP/HTTPS Connection
        HttpURLConnection conn = (this.isSSL) ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        
        // Add parameters to be sent
        OutputStreamWriter connOut = new OutputStreamWriter(conn.getOutputStream());
        StringBuilder auxParams = new StringBuilder(this.parametersString);
        for (HttpParameter parameter : parameters) 
        	auxParams.append(String.format("&%s", parameter.toString()));
        //Send parameters to outer connection
        connOut.write(auxParams.toString());
        connOut.flush();
        connOut.close();
        
        // Check the response code is 200
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) 
        	throw new HTTPException(conn.getResponseCode());
        // Check the content type is application/json
        if (isJson && conn.getContentType().indexOf(JSON_CONTENT_TYPE) == -1) 
        	throw new InvalidResponseException(conn.getContentType());
        
        // Return the connection InputStream
        return conn.getInputStream();
    }
    /**
     * Casts an InputStream returned by method {@link #connect(ApiMethod, List) connect} to a valid SubsonicResponse
     * @param in InputStream corresponding to a previous {@link #connect(ApiMethod, List) connect} call
     * @param responseClass Type of the class that extends from SubsonicResponse and has to be returned
     * @return An object extending SubsonicResponse that results from mapping the returned JSON object into a Java object
     * @throws IOException 
     * @throws SubsonicException If a Subsonic error occurs
     * @throws InvalidResponseException 
     */
    private <T extends SubsonicResponse> T parseResponse(InputStream in, Class<T> responseClass) throws IOException, SubsonicException, InvalidResponseException {
        T response = null;
    	
        //Parse Subsonic response to String
        StringBuilder resp 		= new StringBuilder();
        BufferedReader reader	= new BufferedReader(new InputStreamReader(in));
        String singleLine;
        while ((singleLine = reader.readLine()) != null) 
        	resp.append(singleLine);
        JsonElement responseElement = this.parser.parse(StringEscapeUtils.unescapeXml(resp.toString())).getAsJsonObject().get(SUBSONIC_RESPONSE_IDENTIFIER);
        
        // Parse Subsonic string response to its corresponding Java Object and check if an error occured
        try {
        	response = this.gson.fromJson(responseElement, responseClass);
        } catch (JsonSyntaxException e) {
        	throw new InvalidResponseException(e);
        }
        if (response.getStatus().equalsIgnoreCase(SubsonicResponse.STATUS_FAILED)) {
        	int code 		= responseElement.getAsJsonObject().get("error").getAsJsonObject().get("code").getAsInt();
        	String message	= responseElement.getAsJsonObject().get("error").getAsJsonObject().get("message").getAsString();
        	throw new SubsonicException(code, message);
        }
        // Close connection input after finishing reading it
        in.close();
        
        // Return  mapped response
        return response;
    }
    
    /**
     * Checks if a version is compatible with current server version. If it is compatible, it returns the version object, if it is not it throws a CompatibilityException
     * @param version The version to be checked
     * @return The provided version object
     * @throws CompatibilityException in case specified version is not compatible with current server's version
     */
    private Version getVersionCompatible(Version version) throws CompatibilityException {
    	if (!this.isCompatible(version))
    		throw new CompatibilityException();
    	
    	return version;
    }
    /**
     * Checks if the current server version is greater than or equal than the specified version
     * @param methodVersion The method version to compare with current server version
     * @return True if the current server version is greater than or equal than the specified version. False otherwise
     */
    private boolean isCompatible(Version methodVersion) {
    	if (this.serverApiVersion == null) return true;
    	return (this.serverApiVersion.compareTo(methodVersion) >= 0);
    }
    
    /**
     * Initializes de current server API version by calling to PING method and getting the version property
     */
    private void initApiVersion() {
    	try {
    		SubsonicResponse resp = this.parseResponse(this.connect(ApiMethod.PING), SubsonicResponse.class);
    		this.serverApiVersion = resp.getVersion();
		} catch (Exception e) {
			this.serverApiVersion = new Version(1);
		}
    }
    
    /**
     * Returns current server API version
     * @return Current server API version
     */
    public Version getApiVersion() {
    	return this.serverApiVersion;
    }
    
    @Override
	public boolean ping() {
    	try {
        	SubsonicResponse resp = this.parseResponse(this.connect(ApiMethod.PING), SubsonicResponse.class);        	
        	return (resp.getStatus().equalsIgnoreCase(SubsonicResponse.STATUS_OK));
        } catch(Exception e) {
        	return false;
        }
    }
    
    @Override
	public GetLicenseResponse getLicense() throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
    	return this.parseResponse(this.connect(ApiMethod.GET_LICENSE), GetLicenseResponse.class);
    }
    
    @Override
	public GetMusicFoldersResponse getMusicFolders() throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
    	return this.parseResponse(this.connect(ApiMethod.GET_MUSIC_FOLDERS), GetMusicFoldersResponse.class);
    }
    
    @Override
	public GetIndexesResponse getIndexes() throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
        return this.getIndexes("-1", 0);
    }
    @Override
	public GetIndexesResponse getIndexes(String musicFolderId) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
        return this.getIndexes(musicFolderId, 0);
    }
    @Override
	public GetIndexesResponse getIndexes(long modifiedSince) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
        return this.getIndexes("-1", modifiedSince);
    }
    @Override
    public GetIndexesResponse getIndexes(String musicFolderId, long modifiedSince) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Add params
        parameters.add(new HttpParameter("ifModifiedSince", String.valueOf(modifiedSince)));
        // Set music folder if defined
        if (!musicFolderId.equals("-1")) 
        	parameters.add(new HttpParameter("musicFolderId", musicFolderId));
        return this.parseResponse(this.connect(ApiMethod.GET_INDEXES, parameters), GetIndexesResponse.class);
    }
    
    @Override
	public GetMusicDirectoryResponse getMusicDirectory(String uniqueFolderId) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Set params
        parameters.add(new HttpParameter("id", uniqueFolderId));
        return this.parseResponse(this.connect(ApiMethod.GET_MUSIC_DIRECTORY, parameters), GetMusicDirectoryResponse.class);
    }
    
    @Override
	public SearchResponse search(String query) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
        return this.search(query, 0, 0);
    }
    @Override
	public SearchResponse search(String query, int count) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	return this.search(query, count, 0);
    }
    @Override
	public SearchResponse search(String query, int count, int offset) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Set params
        parameters.add(new HttpParameter("query", query));
        if (count > 0) {
            parameters.add(new HttpParameter("artistCount"	, String.valueOf(count)));
            parameters.add(new HttpParameter("artistOffset"	, String.valueOf(offset)));
            parameters.add(new HttpParameter("albumCount"	, String.valueOf(count)));
            parameters.add(new HttpParameter("albumOffset"	, String.valueOf(offset)));
            parameters.add(new HttpParameter("songCount"	, String.valueOf(count)));
            parameters.add(new HttpParameter("songOffset"	, String.valueOf(offset)));
        }
        return this.parseResponse(this.connect(ApiMethod.SEARCH_2, parameters), SearchResponse.class);
    }    
    
    @Override
	public GetPlaylistsResponse getPlaylists() throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
        return this.parseResponse(this.connect(ApiMethod.GET_PLAYLISTS),GetPlaylistsResponse.class);
    }
    @Override
	public GetPlaylistResponse getPlaylist(String playlistId) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Set params
        parameters.add(new HttpParameter("id", playlistId));
        return this.parseResponse(this.connect(ApiMethod.GET_PLAYLIST, parameters), GetPlaylistResponse.class);
    }
    
    @Override
	public SubsonicResponse createPlaylist(List<String> songsList, String name) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException 
    {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Set params
        parameters.add(new HttpParameter("name", name));
        for (String song : songsList)
            parameters.add(new HttpParameter("songId", song));
        return this.parseResponse(this.connect(ApiMethod.CREATE_PLAYLIST, parameters), SubsonicResponse.class);
    }
    
    @Override
	public SubsonicResponse deletePlaylist(String playlistId) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Set params
        parameters.add(new HttpParameter("id", playlistId));
        return this.parseResponse(this.connect(ApiMethod.DELETE_PLAYLIST, parameters), SubsonicResponse.class);
    }
    
    @Override
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
        return this.getAlbumsList(type, 10, 0);
    }
    @Override
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	return this.getAlbumsList(type, size, 0);
    }
    @Override
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size, int offset) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException 
    {
    	//Check type compatibility
    	if (!this.isCompatible(type.getMinVersion()))	
    		throw new CompatibilityException();
    	
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Set params
        parameters.add(new HttpParameter("type"		, type.toString()));
        parameters.add(new HttpParameter("size"		, String.valueOf(size)));
        parameters.add(new HttpParameter("offset"	, String.valueOf(offset)));
        return this.parseResponse(this.connect(ApiMethod.GET_ALBUM_LIST, parameters), GetAlbumsResponse.class);
    }
    
    @Override
	public GetRandomSongsResponse getRandomSongs() throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException
    {
    	return this.getRandomSongs("-1", 10);
    }
    @Override
	public GetRandomSongsResponse getRandomSongs(String folderId) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException
    {
    	return this.getRandomSongs(folderId, 10);
    }
    @Override
	public GetRandomSongsResponse getRandomSongs(String folderId, int size) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(new HttpParameter("size", String.valueOf(size)));
        if (!folderId.equals("-1"))
        	parameters.add(new HttpParameter("musicFolderId", folderId));
        return this.parseResponse(this.connect(ApiMethod.GET_RANDOM_SONGS, parameters), GetRandomSongsResponse.class);
    }
    
    @Override
	public GetPodcastsResponse getPodcasts() throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(new HttpParameter("v", this.getVersionCompatible(ApiMethod.GET_PODCASTS.getVersion()).toString(true)));
        return this.parseResponse(this.connect(ApiMethod.GET_PODCASTS, parameters), GetPodcastsResponse.class);
    }
    
    @Override
	public GetPodcastResponse getPodcastEpisodes(String podcastId) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	//Get all podcasts
    	GetPodcastsResponse podcastsResponse	= this.getPodcasts();
    	GetPodcastResponse podcastResponse		= new GetPodcastResponse();
        
    	//Fetch the specified podcast in the podcasts array
    	for(ChannelInfo channel : podcastsResponse.getPodcasts().getChannelsArray()) {
    		if (channel.getId().equals(podcastId)) {
    			podcastResponse.setChannel(channel)
    						   .setStatus(podcastsResponse.getStatus())
    						   .setVersion(podcastsResponse.getVersion());
    			break;
    		}
    	}
    	
    	return podcastResponse;
    }
    
    @Override
	public SubsonicResponse refreshPodcasts() throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
        return this.parseResponse(this.connect(ApiMethod.REFRESH_PODCASTS), SubsonicResponse.class);
	}
    
    @Override
	public SubsonicResponse createPodcastChannel(String url) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
		return this.createPodcastChannel(new URL(url));
	}
	@Override
	public SubsonicResponse createPodcastChannel(URL url) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
		List<HttpParameter> parameters = new ArrayList<HttpParameter>();
		// Set params
        parameters.add(new HttpParameter("url", url.toString()));
        return this.parseResponse(this.connect(ApiMethod.CREATE_PODCAST, parameters), SubsonicResponse.class);
	}
    
	@Override
	public SubsonicResponse deletePodcastChannel(String channelId) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
		List<HttpParameter> parameters = new ArrayList<HttpParameter>();
		// Set params
    	parameters.add(new HttpParameter("id", channelId));
    	return this.parseResponse(this.connect(ApiMethod.DELETE_PODCAST, parameters), SubsonicResponse.class);
	}
	
    @Override
	public SubsonicResponse setRating(AlbumRating rating) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Set params
        parameters.add(new HttpParameter("id"		, rating.getAlbumId()));
        parameters.add(new HttpParameter("rating"	, String.valueOf(rating.getRating())));
        return this.parseResponse(this.connect(ApiMethod.SET_RATING, parameters), SubsonicResponse.class);
    }
    
	@Override
	public SubsonicResponse star(String id) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
		List<HttpParameter> parameters = new ArrayList<HttpParameter>();
		// Set params
    	parameters.add(new HttpParameter("id", id));
    	return this.parseResponse(this.connect(ApiMethod.STAR, parameters), SubsonicResponse.class);
	}
	
	@Override
	public SubsonicResponse unstar(String id) throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
		List<HttpParameter> parameters = new ArrayList<HttpParameter>();
		// Set params
    	parameters.add(new HttpParameter("id", id));
    	return this.parseResponse(this.connect(ApiMethod.UNSTAR, parameters), SubsonicResponse.class);
	}
	
	@Override
	public GetStarredResponse getStarred() throws IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
		return this.parseResponse(this.connect(ApiMethod.GET_STARRED), GetStarredResponse.class);
	}
	
    @Override
	public InputStream download(String uniqueId) throws HTTPException, IOException, InvalidResponseException, CompatibilityException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	// Set params
        parameters.add(new HttpParameter("id", uniqueId));
        return this.connect(ApiMethod.DOWNLOAD, parameters, false);
    }
    
    @Override
	public InputStream stream(String uniqueId) throws HTTPException, IOException, InvalidResponseException, CompatibilityException {
        return this.stream(uniqueId, 0);
    }
    @Override
	public InputStream stream(String uniqueId, int maxBitRate) throws IOException, InvalidResponseException, HTTPException, CompatibilityException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(new HttpParameter("id"			, uniqueId));
        parameters.add(new HttpParameter("maxBitRate"	, String.valueOf(maxBitRate)));
        return this.connect(ApiMethod.STREAM, parameters, false);
    }
    
    @Override
	public String getStreamURL(String uniqueId) {
    	return this.getStreamURL(uniqueId, 0);
    }
    @Override
	public String getStreamURL(String uniqueId, int maxBitRate) {
        String urlPath 	= this.serverURL.toString() + ApiMethod.STREAM.toString(),
        	   params 	= String.format("%s&v=%s&id=%s&maxBitRate=%s", this.parametersString, ApiMethod.STREAM.getVersion().toString(true), uniqueId, maxBitRate);
        
        return String.format("%s?%s", urlPath, params);
    }
    
    @Override
	public BufferedImage getCoverArt(String coverId) throws IOException, InvalidResponseException, HTTPException, CompatibilityException {
        return this.getCoverArt(coverId, 100);
    }
    @Override
	public BufferedImage getCoverArt(String coverId, int size) throws IOException, InvalidResponseException, HTTPException, CompatibilityException {
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(new HttpParameter("id"	, coverId));
        parameters.add(new HttpParameter("size"	, String.valueOf(size)));
        return ImageIO.read(this.connect(ApiMethod.GET_COVER_ART, parameters, false));
    }
    
	@Override
	public GetBookmarksResponse getBookmarks() throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
		return this.parseResponse(this.connect(ApiMethod.GET_BOOKMARKS), GetBookmarksResponse.class);
	}
	@Override
	public SubsonicResponse createBookmark(String mediaId, long position) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
		List<HttpParameter> parameters = new ArrayList<HttpParameter>();
		// Set params
		parameters.add(new HttpParameter("id"		, mediaId));
    	parameters.add(new HttpParameter("position"	, String.valueOf(position)));
    	return this.parseResponse(this.connect(ApiMethod.CREATE_BOOKMARK, parameters), SubsonicResponse.class);
	}
	@Override
	public SubsonicResponse deleteBookmark(String mediaId) throws IOException, SubsonicException, InvalidResponseException, HTTPException, CompatibilityException {
		List<HttpParameter> parameters = new ArrayList<HttpParameter>();
		// Set params
		parameters.add(new HttpParameter("id", mediaId));
    	return this.parseResponse(this.connect(ApiMethod.DELETE_BOOKMARK, parameters), SubsonicResponse.class);
	}
	
}