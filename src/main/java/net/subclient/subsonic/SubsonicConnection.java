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

import net.subclient.subsonic.exceptions.CompatibilityException;
import net.subclient.subsonic.exceptions.InvalidResponseException;
import net.subclient.subsonic.exceptions.SubsonicException;
import net.subclient.subsonic.factories.GsonFactory;
import net.subclient.subsonic.mappings.ChannelInfo;
import net.subclient.subsonic.responses.GetAlbumsResponse;
import net.subclient.subsonic.responses.GetIndexesResponse;
import net.subclient.subsonic.responses.GetLicenseResponse;
import net.subclient.subsonic.responses.GetMusicDirectoryResponse;
import net.subclient.subsonic.responses.GetMusicFoldersResponse;
import net.subclient.subsonic.responses.GetPlaylistResponse;
import net.subclient.subsonic.responses.GetPlaylistsResponse;
import net.subclient.subsonic.responses.GetPodcastResponse;
import net.subclient.subsonic.responses.GetPodcastsResponse;
import net.subclient.subsonic.responses.GetRandomSongsResponse;
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
	
	/* Supported Subsonic API functions */
	private static final String REST_PREFIX			= "/rest/";
	private static final String PING 				= REST_PREFIX + "ping.view";
	private static final String GET_LICENSE 		= REST_PREFIX + "getLicense.view";
	private static final String GET_MUSIC_FOLDERS 	= REST_PREFIX + "getMusicFolders.view";
	private static final String GET_INDEXES 		= REST_PREFIX + "getIndexes.view";
	private static final String GET_MUSIC_DIRECTORY	= REST_PREFIX + "getMusicDirectory.view";
	private static final String SEARCH_2			= REST_PREFIX + "search2.view";
	private static final String GET_PLAYLISTS 		= REST_PREFIX + "getPlaylists.view";
	private static final String GET_PLAYLIST 		= REST_PREFIX + "getPlaylist.view";
	private static final String CREATE_PLAYLIST 	= REST_PREFIX + "createPlaylist.view";
	private static final String DELETE_PLAYLIST 	= REST_PREFIX + "deletePlaylist.view";
	private static final String DOWNLOAD 			= REST_PREFIX + "download.view";
	private static final String STREAM 				= REST_PREFIX + "stream.view";
	private static final String GET_COVER_ART 		= REST_PREFIX + "getCoverArt.view";
	private static final String GET_ALBUM_LIST 		= REST_PREFIX + "getAlbumList.view";
	private static final String GET_RANDOM_SONGS	= REST_PREFIX + "getRandomSongs.view";
	private static final String GET_PODCASTS 		= REST_PREFIX + "getPodcasts.view";
	private static final String SET_RATING 			= REST_PREFIX + "setRating.view";
	
	/** Identifier of the main JSON object in any Subsonic response */
    private static final String SUBSONIC_RESPONSE_IDENTIFIER = "subsonic-response";
    /** JSON Content-type header */
    private static final String JSON_CONTENT_TYPE = "application/json";
    /** Default client identifier value */
    private static final String DEFAULT_CLIENT_IDENTIFIER = "Subclient";
    
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
    	//Generate parameters string
    	StringBuilder paramsBuilder = new StringBuilder();
    	HttpParameter userParam 	= new HttpParameter("u", user);
    	HttpParameter passParam 	= new HttpParameter("p", pass);
    	HttpParameter clientParam	= new HttpParameter("c", clientIdentifier);
    	HttpParameter jsonParam 	= new HttpParameter("f", "json");
    	if(isPassEncoded) passParam.setValue("enc:" + passParam.getValue());
    	paramsBuilder.append(userParam.toString() + "&")
    				 .append(passParam.toString() + "&")
    				 .append(clientParam.toString() + "&")
    				 .append(jsonParam.toString());
    	this.parametersString		= paramsBuilder.toString();
    	
    	//Define JSON object handlers
    	this.gson	= GsonFactory.createDeserializer();
    	this.parser	= new JsonParser();
        
        //If URL uses HTTPS protocol then SSL is assumed
        this.isSSL = (urlObj.getProtocol().equalsIgnoreCase("https")) ? true : false;
        if(this.isSSL) this.setSSLProperties();
        
        //Initialize API version environement
        Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				SubsonicConnection.this.initApiVersion();
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
     * Performs a connection to the server executing specified function and passing provided parameters. It is assumed that JSON must be returned
     * @param function One of the supported functions
     * @param parameters Parametters to be passed to server
     * @return InputStream corresponding the preformed HTTP connection
     * @throws IOException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200
     */
    private InputStream connect(String function, List<HttpParameter> parameters) throws IOException, InvalidResponseException, HTTPException {
    	return this.connect(function, parameters, true);
    }
    /**
     * Performs a connection to the server executing specified function and passing provided parameters.
     * @param function One of the supported functions
     * @param parameters Parametters to be passed to server
     * @param isJson Defines if JSON is expected. It won't on any method returning binary contents
     * @return InputStream corresponding the preformed HTTP connection
     * @throws IOException
     * @throws InvalidResponseException If the Subsonic servers returns a non parseable response 
     * @throws HTTPException If the server response code is other than 200
     */
    private InputStream connect(String function, List<HttpParameter> parameters, boolean isJson) throws IOException, InvalidResponseException, HTTPException {
    	//Generate URL object
    	String urlPath = this.serverURL.toString() + function;
        URL url = new URL(urlPath);
        
        //Open HTTP/HTTPS Connection
        HttpURLConnection conn = (this.isSSL) ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST"); //Establecer POST como método de comunicación
        conn.setDoOutput(true); //Permitir envio de información
        
        //Add parameters to be sent
        OutputStreamWriter connOut = new OutputStreamWriter(conn.getOutputStream());
        StringBuilder auxParams = new StringBuilder(this.parametersString);
        for(HttpParameter parameter : parameters) auxParams.append("&" + parameter.toString());
        //Send parameters to outer connection
        connOut.write(auxParams.toString());
        connOut.flush();
        connOut.close();
        
        //Check the response code is 200
        if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) throw new HTTPException(conn.getResponseCode());
        //Check the content type is application/json
        if(isJson && conn.getContentType().indexOf(JSON_CONTENT_TYPE) == -1) throw new InvalidResponseException(conn.getContentType());
        
        //Return the connection InputStream
        return conn.getInputStream();
    }
    /**
     * Casts an InputStream returned by method {@link #connect(String, List) connect} to a valid SubsonicResponse
     * @param in InputStream corresponding to a previous {@link #connect(String, List) connect} call
     * @param responseClass Type of the class that extends from SubsonicResponse and has to be returned
     * @return An object extending SubsonicResponse that results from mapping the returned JSON object into a Java object
     * @throws IOException 
     * @throws SubsonicException If a Subsonic error occurs
     * @throws JsonSyntaxException
     */
    private <T extends SubsonicResponse> T parseResponse(InputStream in, Class<T> responseClass) throws IOException, JsonSyntaxException, SubsonicException {
        T response = null;
    	
        //Parse Subsonic response to String
        StringBuilder resp = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String singleLine;
        while((singleLine = reader.readLine()) != null) resp.append(singleLine);
        JsonElement responseElement = this.parser.parse(StringEscapeUtils.unescapeXml(resp.toString())).getAsJsonObject().get(SUBSONIC_RESPONSE_IDENTIFIER);
        
        //Parse Subsonic string response to its corresponding Java Object and check if an error occured
        response = this.gson.fromJson(responseElement, responseClass);
        if(response.getStatus().equalsIgnoreCase(SubsonicResponse.STATUS_FAILED)) {
        	int code = responseElement.getAsJsonObject().get("error").getAsJsonObject().get("code").getAsInt();
        	String message = responseElement.getAsJsonObject().get("error").getAsJsonObject().get("message").getAsString();
        	throw new SubsonicException(code, message);
        }
        //Close connection input after finishing reading it
        in.close();
        
        //Return  mapped response
        return response;
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
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	parameters.add(version);
    	
    	try {
    		SubsonicResponse resp = this.parseResponse(this.connect(PING, parameters), SubsonicResponse.class);
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
        Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
    	parameters.add(version);
    	
        try {
        	SubsonicResponse resp = this.parseResponse(this.connect(PING, parameters), SubsonicResponse.class);        	
        	return (resp.getStatus().equalsIgnoreCase(SubsonicResponse.STATUS_OK));
        } catch(Exception e) {
        	return false;
        }
    }
    
    @Override
	public GetLicenseResponse getLicense() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        return this.parseResponse(this.connect(GET_LICENSE, parameters), GetLicenseResponse.class);
    }
    
    @Override
	public GetMusicFoldersResponse getMusicFolders() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        return this.parseResponse(this.connect(GET_MUSIC_FOLDERS, parameters), GetMusicFoldersResponse.class);
    }
    
    @Override
	public GetIndexesResponse getIndexes() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
        return this.getIndexes("-1", 0);
    }
    @Override
	public GetIndexesResponse getIndexes(String musicFolderId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
        return this.getIndexes(musicFolderId, 0);
    }
    @Override
	public GetIndexesResponse getIndexes(long modifiedSince) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
        return this.getIndexes("-1", modifiedSince);
    }
    @Override
    public GetIndexesResponse getIndexes(String musicFolderId, long modifiedSince) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("ifModifiedSince", Long.toString(modifiedSince)));
        if(!musicFolderId.equals("-1")) parameters.add(new HttpParameter("musicFolderId", musicFolderId));
        return this.parseResponse(this.connect(GET_INDEXES, parameters), GetIndexesResponse.class);
    }
    
    @Override
	public GetMusicDirectoryResponse getMusicDirectory(String uniqueFolderId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("id", uniqueFolderId));
        return this.parseResponse(this.connect(GET_MUSIC_DIRECTORY, parameters), GetMusicDirectoryResponse.class);
    }
    
    @Override
	public SearchResponse search(String query) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
        return this.search(query, 0, 0);
    }
    @Override
	public SearchResponse search(String query, int count) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	return this.search(query, count, 0);
    }
    @Override
	public SearchResponse search(String query, int count, int offset) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	Version methodApiVersion = new Version(1, 4, 0);
    	//Check compatibility
        if(!this.isCompatible(methodApiVersion)) throw new CompatibilityException();
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("query", query));
        if(count > 0) {
            parameters.add(new HttpParameter("artistCount"	, String.valueOf(count)));
            parameters.add(new HttpParameter("artistOffset"	, String.valueOf(offset)));
            parameters.add(new HttpParameter("albumCount"	, String.valueOf(count)));
            parameters.add(new HttpParameter("albumOffset"	, String.valueOf(offset)));
            parameters.add(new HttpParameter("songCount"	, String.valueOf(count)));
            parameters.add(new HttpParameter("songOffset"	, String.valueOf(offset)));
        }
        return this.parseResponse(this.connect(SEARCH_2, parameters), SearchResponse.class);
    }    
    
    @Override
	public GetPlaylistsResponse getPlaylists() throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
    	Version methodApiVersion = new Version(1, 0, 0);
        HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
        List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        return this.parseResponse(this.connect(GET_PLAYLISTS, parameters),GetPlaylistsResponse.class);
    }
    @Override
	public GetPlaylistResponse getPlaylist(String playlistId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, HTTPException {
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("id", playlistId));
        return this.parseResponse(this.connect(GET_PLAYLIST, parameters), GetPlaylistResponse.class);
    }
    
    @Override
	public SubsonicResponse createPlaylist(List<String> songsList, String name) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException 
    {
    	Version methodApiVersion = new Version(1, 2, 0);
    	//Check compatibility
        if(!this.isCompatible(methodApiVersion)) throw new CompatibilityException();
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
    	//Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("name", name));
        for(String song : songsList) {
            parameters.add(new HttpParameter("songId", song));
        }
        return this.parseResponse(this.connect(CREATE_PLAYLIST, parameters), SubsonicResponse.class);
    }
    
    @Override
	public SubsonicResponse deletePlaylist(String playlistId) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	Version methodApiVersion = new Version(1, 2, 0);
    	//Check compatibility
        if(!this.isCompatible(methodApiVersion)) throw new CompatibilityException();
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("id", playlistId));
        return this.parseResponse(this.connect(DELETE_PLAYLIST, parameters), SubsonicResponse.class);
    }
    
    @Override
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
        return this.getAlbumsList(type, 10, 0);
    }
    @Override
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	return this.getAlbumsList(type, size, 0);
    }
    @Override
	public GetAlbumsResponse getAlbumsList(GetAlbumsType type, int size, int offset) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException 
    {
    	Version methodApiVersion = new Version(1, 2, 0);
    	//Check method compatibility and parameters compatibility
        if(!this.isCompatible(methodApiVersion)) 		throw new CompatibilityException();
        if(!this.isCompatible(type.getMinVersion()))	throw new CompatibilityException();
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
    	
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("type"		, type.toString()));
        parameters.add(new HttpParameter("size"		, String.valueOf(size)));
        parameters.add(new HttpParameter("offset"	, String.valueOf(offset)));
        return this.parseResponse(this.connect(GET_ALBUM_LIST, parameters), GetAlbumsResponse.class);
    }
    
    @Override
	public GetRandomSongsResponse getRandomSongs() 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException
    {
    	return this.getRandomSongs("-1", 10);
    }
    @Override
	public GetRandomSongsResponse getRandomSongs(String folderId) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException
    {
    	return this.getRandomSongs(folderId, 10);
    }
    @Override
	public GetRandomSongsResponse getRandomSongs(String folderId, int size) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException
    {
    	Version methodApiVersion = new Version(1, 2, 0);
    	//Check compatibility
        if(!this.isCompatible(methodApiVersion)) throw new CompatibilityException();
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
    	
    	//Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("size", String.valueOf(size)));
        if(!folderId.equals("-1"))
        	parameters.add(new HttpParameter("musicFolderId", folderId));
        return this.parseResponse(this.connect(GET_RANDOM_SONGS, parameters), GetRandomSongsResponse.class);
    }
    
    @Override
	public GetPodcastsResponse getPodcasts() 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException
    {
    	Version methodApiVersion = new Version(1, 6, 0);
    	//Check compatibility
        if(!this.isCompatible(methodApiVersion)) throw new CompatibilityException();
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        return this.parseResponse(this.connect(GET_PODCASTS, parameters), GetPodcastsResponse.class);
    }
    
    @Override
	public GetPodcastResponse getPodcastEpisodes(String podcastId) 
    		throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException
    {
    	//Get all podcasts
    	GetPodcastsResponse podcastsResponse	= this.getPodcasts();
    	GetPodcastResponse podcastResponse		= new GetPodcastResponse();
        
    	//Fetch the specified podcast in the podcasts array
    	for(ChannelInfo channel : podcastsResponse.getPodcasts().getChannelsArray()) {
    		if(channel.getId().equals(podcastId)) {
    			podcastResponse.setChannel(channel)
    						   .setStatus(podcastsResponse.getStatus())
    						   .setVersion(podcastsResponse.getVersion());
    			break;
    		}
    	}
    	
    	return podcastResponse;
    }
    
    @Override
	public SubsonicResponse setRating(AlbumRating rating) throws JsonSyntaxException, IOException, SubsonicException, InvalidResponseException, CompatibilityException, HTTPException {
    	Version methodApiVersion = new Version(1, 6, 0);
    	//Check compatibility
        if(!this.isCompatible(methodApiVersion)) throw new CompatibilityException();
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("id"		, rating.getAlbumId()));
        parameters.add(new HttpParameter("rating"	, String.valueOf(rating.getRating())));
        return this.parseResponse(this.connect(SET_RATING, parameters), SubsonicResponse.class);
    }
    
    @Override
	public InputStream download(String uniqueId) throws HTTPException, IOException, InvalidResponseException {
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
        //Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("id", uniqueId));
        return this.connect(DOWNLOAD, parameters, false);
    }
    
    @Override
	public InputStream stream(String uniqueId) throws HTTPException, IOException, InvalidResponseException {
        return this.stream(uniqueId, 0);
    }
    @Override
	public InputStream stream(String uniqueId, int maxBitRate) throws IOException, InvalidResponseException, HTTPException {
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
    	//Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("id"			, uniqueId));
        parameters.add(new HttpParameter("maxBitRate"	, String.valueOf(maxBitRate)));
        return this.connect(STREAM, parameters, false);
    }
    
    @Override
	public String getStreamURL(String uniqueId) {
    	return this.getStreamURL(uniqueId, 0);
    }
    @Override
	public String getStreamURL(String uniqueId, int maxBitRate) {
    	Version methodApiVersion = new Version(1, 0, 0);
        String version = "&v=" + methodApiVersion.toString(true);
        String urlPath = this.serverURL.toString() + STREAM;
        String params = this.parametersString + version + "&id=" + uniqueId + "&maxBitRate=" + String.valueOf(maxBitRate);
        String finalUrl = urlPath + "?" + params;
        
        return finalUrl;
    }
    
    @Override
	public BufferedImage getCoverArt(String coverId) throws IOException, InvalidResponseException, HTTPException {
        return this.getCoverArt(coverId, 100);
    }
    @Override
	public BufferedImage getCoverArt(String coverId, int size) throws IOException, InvalidResponseException, HTTPException {
    	Version methodApiVersion = new Version(1, 0, 0);
    	HttpParameter version = new HttpParameter("v", methodApiVersion.toString(true));
        
    	//Send request and return response
    	List<HttpParameter> parameters = new ArrayList<HttpParameter>();
        parameters.add(version);
        parameters.add(new HttpParameter("id"	, coverId));
        parameters.add(new HttpParameter("size"	, String.valueOf(size)));
        return ImageIO.read(this.connect(GET_COVER_ART, parameters, false));
    }

}