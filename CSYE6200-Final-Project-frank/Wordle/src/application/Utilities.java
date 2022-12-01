package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Utilities
{
	public static ArrayList<String> ReadWordsFromFile(String filePath)
	{
		try {
			ArrayList<String> readList = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null)   {
			  readList.add(line.toUpperCase());
			}
			
			br.close();
			return readList;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return new ArrayList<String>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
}