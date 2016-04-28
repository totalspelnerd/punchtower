package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.LoadFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.SaveFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.TagException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles our save file format. Using tags and values.
 */
public class SaveFile
{

	private List<String> tags = new ArrayList<>();
	private List<String> values = new ArrayList<>();
	private String filename;

	public SaveFile(String filename)
	{
		this.filename = filename;
	}

	public void addTag(String tag, String value)
	{
		tags.add(tag);
		values.add(value);
	}

	public String getTag(String tag) throws TagException
	{
		assert (tags.size() == values.size()) :
			"Tags and values doesn't have the same size: " + tags.size() + " : " + values.size();
		for (int i = 0; i < tags.size(); i++) {
			if (tags.get(i).equals(tag)) {
				return values.get(i);
			}
		}
		throw new TagException(tag);
	}

	public int getTagAsInt(String tag) throws TagException
	{
		try {
			return Integer.parseInt(getTag(tag));
		} catch (NumberFormatException e) {
			throw new TagException(tag, e);
		}
	}

	public void saveToFile() throws SaveFailedException
	{
		assert (tags.size() == values.size()) :
			"Tags and values doesn't have the same size: " + tags.size() + " : " + values.size();
		File file = new File(filename);
		try (PrintWriter pw = new PrintWriter(file)) {
			for (int i = 0; i < tags.size(); i++) {
				pw.write(tags.get(i) + "=" + values.get(i) + "\n");
			}
		} catch (IOException e) {
			throw new SaveFailedException(filename, e);
		}
	}

	public void load() throws LoadFailedException
	{
		File file = new File(filename);
		if (!file.exists()) throw new LoadFailedException(filename);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) // This is the standard way to create a file reader
			{
				if (line.contains("=")) {
					String[] values = line.split("=");
					addTag(values[0], values[1]);
				}
			}
			br.close();
		} catch (IOException e) {
			throw new LoadFailedException(filename, e);
		}
	}
}
