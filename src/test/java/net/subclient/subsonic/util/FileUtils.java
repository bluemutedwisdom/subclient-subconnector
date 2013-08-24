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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * 
 * @author Alejandro Celaya Alastrué
 */
public class FileUtils {

	/**
	 * Reads the specified text file and returns their contents in a String
	 * @param path Path to the file to read. It is created if it doesn't exist
	 * @return Contents of the specified file
	 * @throws IOException
	 */
	public static String readTextFile(String path) throws IOException {
		//Read the current contents of the specified file
		File file = new File(path);
		
		//Read the file and keep the content
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder content = new StringBuilder();
		String line = "";
		while((line = reader.readLine()) != null) content.append(line);
		reader.close();
		
		//Return the content of the file
		return content.toString();
	}

}
