Subconnector
======================

Master: [![Build Status](https://travis-ci.org/acelaya/subclient-subconnector.svg?branch=master)](https://travis-ci.org/acelaya/subclient-subconnector)

Develop: [![Build Status](https://travis-ci.org/acelaya/subclient-subconnector.svg?branch=develop)](https://travis-ci.org/acelaya/subclient-subconnector)

Subconnector is a lightweight Java library designed to help you establish connections to [Subsonic](http://www.subsonic.org/pages/index.jsp) servers, by mapping JSON API responses to Java objects.

This library was mainly created to be used in [Subclient](http://www.subclient.net) project, a cross-platform desktop client for Subsonic.

For more information take a look into [Subsonic API documentation](http://www.subsonic.org/pages/api.jsp).

Dependencies
----------------------

Dependencies are handled by [ivy](http://ant.apache.org/ivy/) since Subconnector 2.0.1. They used to be included in this repo in previous versions.

The [ant](http://ant.apache.org/) building script is responsible for downloading all the dependencies by using ivy.

Subconnector depends on this libraries to properly work. Last tested versions are defined in ivy dependencies script, but you can download them from here.

- [Goole GSON](https://code.google.com/p/google-gson/downloads/list)
- [Apache commons Lang3](http://commons.apache.org/proper/commons-lang/download_lang.cgi)

Usage
----------------------

The main object is the `Connection` object. It allows you to preconfigure the connection against the Subsonic server that will be used on each HTTP request.

```java
try {
    URL url = new URL("http://myserver.com:4040");
    // By default, the password is expected to be hex-encoded.
    Connection connection = new SubsonicConnection(url, "username", "70617373776F7264");
} catch (Exception e) {
    e.printStackTrace();
}
```

To provide a plain-text password, a fourth param has to be specified, telling the password is not hex-encoded.

```java
Connection connection = new SubsonicConnection(url, "username", "password", true);
```

Also, a client identifier can be provided to let Subsonic server properly track connections. It is **Subclient** by default.

```java
Connection connection = new SubsonicConnection(url, "username", "70617373776F7264", "MyAppName");
```

`Connection` interface maps all supported Subsonic API methods to Java methods and results are returned as Java objects.

For example, we could retrieve the list of music directories in the server like this.

```java
try {
    URL url = new URL("http://myserver.com:4040");
    Connection connection = new SubsonicConnection(url, "username", "70617373776F7264");
    
    GetMusicFoldersResponse response = connection.getMusicFolders();
    // This response object contains a list of FolderInfo objects, each one of them wraps the name and the ID of one of the folders
    for (FolderInfo folder : response.getMusicFolders().getMusicFoldersArray()) {
        System.out.println(folder.getId());     // This is the ID of the folder
        System.out.println(folder.getName());   // This is the name of the folder
    }
} catch (Exception e) {
    e.printStackTrace();
}
```

Almost all methods defined in `Connection` interface return a response object, either a `SubsonicResponse` object or an object extending `SusbonicResponse`.

By default any of those Response objects can be used to get the status of the response and the version of the API the server is running.

If an error occurs while performing the request, an exception is thrown. Each method has its own exception handling, but all of them can throw at least a `SubsonicException` if the returned response is not valid.

In the previous example this could be done.

```java
try {
    URL url = new URL("http://myserver.com:4040");
    Connection connection = new SubsonicConnection(url, "username", "70617373776F7264");        
    GetMusicFoldersResponse response = connection.getMusicFolders();
    
    // If everything went ok we could get response information at this point.
    System.out.println(String.format("The server version is %s", response.getVersion()));
} catch (SubsonicException e) {
    // The exception contains the error message returned by the Subsonic server
    System.err.println(String.format("Request failed with message %s", e.getMessage()));
} catch (Exception e) {
    e.printStackTrace();
}
```

Subconnector now supports almost any methods for a basic functionality with music folders, podcasts and playlists, as well as item searches.

For a complete Subconnector API reference, please refer to the [Wiki](https://github.com/acelaya/subclient-subconnector/wiki) and the [javadoc](http://www.subclient.net/subconnector-javadoc)

Unit testing
----------------------

This library includes all unit tests of its own in `src/test/java`.

Also, the main `SubsonicConnection` object is designed to be mocked too, by implementing a `Connection` interface.
