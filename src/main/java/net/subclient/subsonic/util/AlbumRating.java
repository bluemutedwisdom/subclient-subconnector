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

package net.subclient.subsonic.util;

/**
 * Steroes an album rating
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class AlbumRating {
    
    /** Album identifier in Subsonic server */
    private String albumId;
    /** Album rating (0 - 5) */
    private int rating;
    
    /**
     * Constructs a new AlbumRating for the specified album and rating = 0
     * @param albumId Album identifier in Subsonic server
     */
    public AlbumRating(String albumId) {
        this(albumId, 0);
    }
    /**
     * Constructs a new AlbumRating for the specified album and rating
     * @param albumId Album identifier in Subsonic server
     * @param rating Rating (0 - 5)
     */
    public AlbumRating(String albumId, int rating) {
        this.albumId 	= albumId;
        this.rating		= this.getValidRating(rating);
    }
    
    public String getAlbumId() {
        return this.albumId;
    }
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
    public int getRating() {
        return this.rating;
    }
    public void setRating(int rating) {
        this.rating = this.getValidRating(rating);
    }
    
    /**
     * Checks if a value is a valid rating from 0 to 5. any value greater than 5 will be 5 and any value lesser than 0 will be 0.
     * @param rating Rating to ve checked
     * @return Valid rating from 0 to 5
     */
    private int getValidRating(int rating) {
        if (rating > 5) rating = 5;
        else if (rating < 0) rating = 0;
        return rating;
    }
}
