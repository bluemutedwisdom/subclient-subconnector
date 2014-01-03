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

import net.subclient.subsonic.mappings.Starred;

/**
 * Object returned on {@link net.subclient.subsonic.Connection#getStarred() getStarred} calls
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class GetStarredResponse extends SubsonicResponse {
	
	private Starred starred;
	
	/**
	 * Constructs a new GetStarredResponse with a default constructed Starred object
	 */
	public GetStarredResponse() {
		super();
		this.starred = new Starred();
	}
	
	public Starred getStarred() {
		return this.starred;
	}
	public GetStarredResponse setStarred(Starred starred) {
		this.starred = starred;
		return this;
	}

}
