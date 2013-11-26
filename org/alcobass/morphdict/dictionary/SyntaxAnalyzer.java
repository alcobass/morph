package org.alcobass.morphdict.dictionary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SyntaxAnalyzer
{

	final static int BUFFER_LENGTH = 1024;
	static SimpleTextDictionary sd;
	//char[] buffer = new char[1024];
	public static ArrayList<SingleWordNode> parseStream(InputStream stream)
	        throws IOException// throws IOException
	{
		if (stream == null)
			return null;
		//stream.r
		//BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

		byte[] buffer = new byte[BUFFER_LENGTH];
		//int byteLeft = 0;
		ArrayList<SingleWordNode> res = new ArrayList<SingleWordNode>();
		String leftString = "";

		boolean readOk = true;
		
		while (readOk)
		{
			String curStr = "";
			int bytesRead = stream.read(buffer, 0, BUFFER_LENGTH);

			//int bytesRead = reader.
			if (bytesRead != -1)
			{
				curStr = new String(buffer, 0, bytesRead);
				
				boolean needLeft = false;
				if (curStr.charAt(curStr.length() - 1) != ' ')
					needLeft = true;

				curStr = leftString + curStr;

				String[] wordList = curStr.split("[ ,./?!\n\r\t:;\'\"()]+");
				
				int lastIndex = wordList.length - 1;
				
				if (needLeft)
				{
					leftString = wordList[lastIndex];
					lastIndex--;
				}
				
				for (int i=0; i<=lastIndex; i++)
				{
					SingleWordNode wn = new SingleWordNode(wordList[i]);
					if (sd.getWordsWithLetters(wordList[i].toCharArray()).contains(wordList[i]))
						wn.setInDictionary(true);
					res.add(wn);
				}
			}
			else
			{
				readOk = false;
				SingleWordNode wn = new SingleWordNode(leftString);
				if (sd.getWordsWithLetters(leftString.toCharArray()).contains(leftString))
					wn.setInDictionary(true);

				res.add(wn);
			}
			
			
			//if ()

		}

		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		sd = new SimpleTextDictionary();
		sd.loadFromZal("lexeme.short.utf");
		
		sd.saveBitVectors();
		
		
		System.out.println(sd.getWordsByMorphFlags(01L));
		
		return;
	}

}
