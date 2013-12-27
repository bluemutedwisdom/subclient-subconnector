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
