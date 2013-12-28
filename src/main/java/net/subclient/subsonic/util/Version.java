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
 * Generates an object to handle a version
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class Version implements Comparable<Version> {
		
	/** Major number of this version */
	private int majorNumber;
	/** Minor number of this version */
	private int minorNumber;
	/** Revision number of this version */
	private int revisionNumber;
	
	/**
	 * Constructs a new Version object with specified major number and assuming minor number and revision number are 0
	 * @param majorNumber
	 */
	public Version(int majorNumber) {
		this(majorNumber, 0, 0);
	}
	/**
	 * Constructs a new Version object with specified major number and minor number and assumming revision number is 0
	 * @param majorNumber
	 */
	public Version(int majorNumber, int minorNumber) {
		this(majorNumber, minorNumber, 0);
	}
	/**
	 * Constructs a new Version object with specified major number, minor number and revision number
	 * @param majorNumber
	 * @param minorNumber
	 * @param revisionNumber
	 */
	public Version(int majorNumber, int minorNumber, int revisionNumber) {
		this.majorNumber = majorNumber;
		this.minorNumber = minorNumber;
		this.revisionNumber = revisionNumber;
	}
	
	/**
	 * 
	 * @return This version's major number
	 */
	public int getMajorNumber() {
		return this.majorNumber;
	}
	/**
	 * 
	 * @return This version's minor number
	 */
	public int getMinorNumber() {
		return this.minorNumber;
	}
	/**
	 * 
	 * @return This version's revision number
	 */
	public int getRevisionNumber() {
		return this.revisionNumber;
	}
	
	@Override
	public int compareTo(Version version) throws IllegalArgumentException {
        if (version == null)
            throw new IllegalArgumentException();
        
        if (this.majorNumber > version.getMajorNumber())
            return 1;
        else if (this.majorNumber < version.getMajorNumber())
            return -1;
        else if (this.minorNumber > version.getMinorNumber())
            return 1;
        else if (this.minorNumber < version.getMinorNumber())
            return -1;
        else if (this.revisionNumber > version.getRevisionNumber())
            return 1;
        else if (this.revisionNumber < version.getRevisionNumber())
            return -1;
        else return 0;
    }
	
	/**
	 * Constructs a Version object from the formatted version string provided
	 * @param formatedVersion The version formatted as X.X.X, X.X or X
	 * @return Version object created from the formatted string
	 * @throws IllegalArgumentException If the String is not a valid formatted version
	 */
	public static Version parseVersion(String formatedVersion) throws IllegalArgumentException {
		String[] parts = formatedVersion.split("\\.");
		int majorNumber 	= 0,
			minorNumber 	= 0,
			revisionNumber	= 0;
		if(parts.length < 1 || parts.length > 3) throw new IllegalArgumentException();
		
		try {
			//Parse version parts to int
			if (parts.length == 1)
				majorNumber 	= Integer.parseInt(parts[0]);
			else if (parts.length == 2) {
				majorNumber 	= Integer.parseInt(parts[0]);
				minorNumber 	= Integer.parseInt(parts[1]);
			} else if (parts.length == 3) {
				majorNumber 	= Integer.parseInt(parts[0]);
				minorNumber 	= Integer.parseInt(parts[1]);
				revisionNumber	= Integer.parseInt(parts[2]);
			} else throw new IllegalArgumentException();
			
			return new Version(majorNumber, minorNumber, revisionNumber);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns the String representation of this version formatted as X.X.X but avoiding revision numbers with zero value. Version 1.2.0 will be represented as 1.2
	 * @return String representation of this Version
	 */
	@Override
	public String toString() {
		return this.toString(false);
	}
	/**
	 * Returns the String representation of this version formatted as X.X.X
	 * @param printComplete If false zeroes revision numbers will be avoided
	 * @return String representation of this Version
	 */
	public String toString(boolean printComplete) {
		return (this.revisionNumber > 0 || printComplete) 											?
				String.format("%s.%s.%s", this.majorNumber, this.minorNumber, this.revisionNumber)	:
				String.format("%s.%s", this.majorNumber, this.minorNumber);
	}
}
