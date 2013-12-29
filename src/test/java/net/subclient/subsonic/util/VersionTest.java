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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * {@link net.subclient.subsonic.util.Version Version} Test Case
 * @author Alejandro Celaya Alastrué 
 */
public class VersionTest {
	
	@Test
	public void testCompareTo() {
		Version version1 = new Version(1, 2),
				version2 = new Version(2, 4, 8);
		assertTrue(version1.compareTo(version2) < 0);
		assertTrue(version2.compareTo(version1) > 0);
		assertTrue(version2.compareTo(version2) == 0);
	}

	@Test
	public void testParseVersion() {
		String formatedVersion = "3";
		Version version = Version.parseVersion(formatedVersion);
		assertEquals(3, version.getMajorNumber());
		assertEquals(0, version.getMinorNumber());
		assertEquals(0, version.getRevisionNumber());
		
		formatedVersion = "14.6.2";
		version = Version.parseVersion(formatedVersion);
		assertEquals(14, version.getMajorNumber());
		assertEquals(6, version.getMinorNumber());
		assertEquals(2, version.getRevisionNumber());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testParseVersionException() {
		String formatedVersion = "Invalid string version";
		Version.parseVersion(formatedVersion);
	}

	@Test
	public void testToString() {
		Version version = new Version(1, 2, 3);
		assertEquals("1.2.3", version.toString());
		
		version = new Version(5);
		assertEquals("5.0", version.toString());
		assertEquals("5.0", version.toString(false));
		assertEquals("5.0.0", version.toString(true));
		
		version = new Version(8, 2);
		assertEquals("8.2", version.toString());
		assertEquals("8.2", version.toString(false));
		assertEquals("8.2.0", version.toString(true));
	}

}
