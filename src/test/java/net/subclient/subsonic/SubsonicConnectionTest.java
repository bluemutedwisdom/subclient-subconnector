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

package net.subclient.subsonic;

import static junit.framework.Assert.assertEquals;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class SubsonicConnectionTest {
	
	private Connection conn;
	
	@Before
	public void setUp() throws Exception {
		this.conn = new SubsonicConnection(new URL("http://foo.bar.com"), "user", "pass");
	}

	@Test
	public void testGetStreamURL() {
		String id 			= "4568",
			   expectedUrl	= "http://foo.bar.com/rest/stream.view?u=user&p=enc:pass&c=Subclient&f=json&v=1.0.0&id=4568&maxBitRate=0",
			   realUrl		= this.conn.getStreamURL(id);
		
		assertEquals(expectedUrl, realUrl);
	}

}
