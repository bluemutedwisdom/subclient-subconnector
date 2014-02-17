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

package net.subclient.subsonic.util;

/**
 * Creates a key-value pair whose string representation is key=value.
 * It is used on HTTP connections to store those connections parameters
 * @author Alejandro Celaya Alastrué
 * @see <a href="http://www.alejandrocelaya.com">www.alejandrocelaya.com</a>
 */
public class HttpParameter {
    
	/** Parameter key */
	private String key;
	/** Parameter value */
	private String value;
	
    /**
     * Constructs a new parameter with the specified key and value
     * @param key Key of this parameter
     * @param value Value of this parameter
     */
    public HttpParameter(String key, String value) {
        this.key 	= key;
        this.value	= value;
    }
    
    /**
     * Return this parameter's key
     * @return This parameter's key
     */
    public String getKey() {
        return this.key;
    }
    /**
     * Sets this parameter's key
     * @param key New key
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * Returns this parameter's value
     * @return This parameter's value
     */
    public String getValue() {
        return this.value;
    }
    /**
     * Sets this parameter's value
     * @param value New value
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return String.format("%s=%s", this.key, this.value);
    }
}
