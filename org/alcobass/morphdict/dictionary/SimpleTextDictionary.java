package org.alcobass.morphdict.dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SimpleTextDictionary implements Dictionary
{

	/**
	 * Russian letters set
	 */
	String russianLetters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя-";
	
	private final static String LETTER_BIT_ARRAY_FILE_PATTERN = "out/letter_%i.out";
	private final static String MORPH_BIT_ARRAY_FILE_PATTERN = "out/morph_%i.out";

	/**
	 * Full list of words
	 */
	private ArrayList<String> wordList;
	// private HashMap<Character, Integer> letterOffset = new HashMap<Character,
	// Integer>();

	/**
	 * Bit-arrays for letters
	 */
	ArrayList<char[]> letterBitArrays;
	ArrayList<Long> params = new ArrayList<Long>();

	/**
	 * Bit-arrays for morphological markers
	 */
	ArrayList<char[]> morphArray;

	int extraWords = 0;

	private final char masks[] =
	{ 128, 64, 32, 16, 8, 4, 2, 1 };

	public SimpleTextDictionary()
	{

	}

	private void loadWordArrayFromZal(String fileName)
	{
		wordList = new ArrayList<String>();
		try
		{
			FileInputStream file = new FileInputStream(fileName);

			int cnt = 0;
			Scanner sc = new Scanner(file);
			System.out.println("Loading dictionary...");
			while (sc.hasNextLine())
			{
				// TODO - infinitive
				String cur = sc.nextLine();
				String[] el = cur.split(",");
				Long toAdd = 0L;
				for (int i = 2; i < el.length; i++)
				{
					try
					{
						if (el[i] != null && !el[i].isEmpty())
							toAdd = toAdd | MorphologicalFlags.DictToFlag.get(el[i]);

					} catch (NullPointerException npe)
					{
						System.out.println("shit happened");
					}
				}
				params.add(toAdd);
				wordList.add(el[0]);
				cnt++;
				if (cnt % 1000000 == 0)
					System.out.println(cnt + " words done");

			}

			sc.close();

			int left = extraWords = wordList.size() % 8;
			for (; left < 8 && left > 0; left++)
			{
				wordList.add("");
				params.add(0L);
			}

		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");

	}

	/**
	 * Load dictionary from Zalyznyak dictionary
	 * @param fileName - file to load from (lexeme.short.utf)
	 */
	public void loadFromZal(String fileName)
	{
		loadWordArrayFromZal(fileName);
		initBitArrays();
		System.out.println("fuck off");
	}

	/**
	 * Process single word while loading from Zalyznyak dictionary
	 * @param cur - current index
	 */
	void buildSingleWordBitArray(int cur)
	{
		String word = wordList.get(cur);
		int index = cur / 8;
		int mask = masks[cur % 8];
		Long letters = 0L;
		for (int i = 0; i < word.length(); i++)
		{
			char k = word.charAt(i);
			int listIndex = russianLetters.indexOf(k);

			if (listIndex == -1)
			{
				System.out.println("Bad symbol " + k);
				continue;
			}
			letterBitArrays.get(listIndex)[index] = (char) (letterBitArrays.get(listIndex)[index] | masks[cur % 8]);
			letters |= 1L << listIndex;

		}
		/*
		if (sameCount.containsKey(letters))
		{
			sameCount.put(letters, sameCount.get(letters) + 1);
		} else
		{
			sameCount.put(letters, 1);
		}
		*/
		for (int i = 0; i < MorphologicalFlags.ParArraySize; i++)
		{
			long fl = 1L << i;
			if ((params.get(cur) & fl) != 0)
				morphArray.get(i)[index] = (char) (morphArray.get(i)[index] | mask);
		}
	}

	/**
	 * Create bit-arrays by word list
	 */
	void initBitArrays()
	{
		System.out.println("Initializing bit arrays...");
		int singleArraySize = wordList.size() / 8;
		letterBitArrays = new ArrayList<char[]>();
		for (int i = 0; i < russianLetters.length(); i++)
		{
			letterBitArrays.add(new char[singleArraySize]);

		}

		morphArray = new ArrayList<char[]>();
		for (int i = 0; i < MorphologicalFlags.ParArraySize; i++)
		{
			morphArray.add(new char[singleArraySize]);
		}

		int cur = 0;
		//HashMap<Long, Integer> sameCount = new HashMap<Long, Integer>();
		for (cur = 0; cur < wordList.size(); cur++)
		{
			buildSingleWordBitArray(cur);
			if (cur % 1000000 == 0)
				System.out.println(cur + " words done");
		}
		System.out.println("Done initializing bit arrays");

	}

	public void loadBitVectors()
	{
		try
		{
			letterBitArrays = new ArrayList<char[]>();
			for (int i=0; i<russianLetters.length(); i++) 
			{
				FileInputStream fis = new FileInputStream(new File(String.format(LETTER_BIT_ARRAY_FILE_PATTERN, i)));
				char[] curLetterBitArray = new char[wordList.size() / 8 + 1];
				while ()FileInputStream fis = new FileInputStream(new File(String.format(LETTER_BIT_ARRAY_FILE_PATTERN, i)));
			}
			
		}
		catch (IOException e) 
		{
			
		}
	}
	
	public void saveBitVectors()
	{
		try
		{
			for (int i = 0; i < letterBitArrays.size(); i++)
			{
				FileOutputStream f = new FileOutputStream(new File(String.format(LETTER_BIT_ARRAY_FILE_PATTERN, i)));
				char[] arr = letterBitArrays.get(i);
				for (int j = 0; j < arr.length; j++)
					f.write((byte) (arr[j]));
				f.close();
			}
			for (int i = 0; i < morphArray.size(); i++)
			{
				FileOutputStream f = new FileOutputStream(new File(String.format(MORPH_BIT_ARRAY_FILE_PATTERN, i)));
				char[] arr = morphArray.get(i);
				for (int j = 0; j < arr.length; j++)
					f.write((byte) (arr[j]));
				f.close();
			}
		} catch (IOException e)
		{

			System.out.println(e);
		}
	}

	@Override
	public boolean isWordInDictionary(String word)
	{
		return wordList.contains(word);
	}

	/**
	 * Get words with same letters
	 * @param letters
	 * @return list of words
	 */
	public List<String> getWordsWithLetters(char[] letters)
	{
		ArrayList<String> res = new ArrayList<String>();

		if (letters == null)
			return res;

		char[] andResult = new char[wordList.size() / 8];

		// Fill with nulls
		for (int i = 0; i < andResult.length; i++)
			andResult[i] = 0xFF;

		for (int i = 0; i < letters.length; i++)
		{
			if (russianLetters.indexOf(letters[i]) == -1)
			{
				continue;
			}

			// int offset = letterOffset.get(letters[i]);

			char letter[] = letterBitArrays.get(russianLetters.indexOf(letters[i]));

			for (int j = 0; j < andResult.length; j++)
			{
				andResult[j] = (char) (((byte) andResult[j]) & ((byte) letter[j]));
			}
		}

		for (int i = 0; i < andResult.length; i++)
		{
			if (andResult[i] == 0x00)
				continue;

			for (int j = 0; j < 8; j++)
			{
				if ((andResult[i] & masks[j]) != 0x00)
					res.add(wordList.get(i * 8 + j));
				;
			}
		}

		return res;
	}

	/**
	 * Get words with same morphological parameters
	 * @param flags - morphological flags
	 * @return list of words
	 */
	public List<String> getWordsByMorphFlags(Long flags)
	{
		List<String> res = new ArrayList<String>();

		char[] andResult = new char[wordList.size() / 8];

		// Fill with nulls
		for (int i = 0; i < andResult.length; i++)
			andResult[i] = 0xFF;

		List<Integer> columns = MorphologicalFlags.getIndexesForFlagSet(flags);

		for (int i = 0; i < columns.size(); i++)
		{
			char cur[] = morphArray.get(columns.get(i));
			for (int j = 0; j < andResult.length; j++)
			{
				andResult[j] = (char) (((byte) andResult[j]) & ((byte) cur[j]));
			}
		}
		for (int i = 0; i < andResult.length; i++)
		{
			if (andResult[i] == 0x00)
				continue;

			for (int j = 0; j < 8; j++)
			{
				if ((andResult[i] & masks[j]) != 0x00)
					res.add(wordList.get(i * 8 + j));
				;
			}
		}
		return res;
	}
}
