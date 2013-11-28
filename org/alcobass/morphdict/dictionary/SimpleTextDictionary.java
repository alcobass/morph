package org.alcobass.morphdict.dictionary;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SimpleTextDictionary implements Dictionary
{

	/**
	 * Russian letters set
	 */
	String russianLetters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя-";
	
	private final static String LETTER_BIT_ARRAY_FILE_PATTERN = "out/letter_%d.dat";
	private final static String MORPH_BIT_ARRAY_FILE_PATTERN = "out/morph_%d.dat";

	/**
	 * Full list of words
	 */
	private ArrayList<String> wordList;
	// private HashMap<Character, Integer> letterOffset = new HashMap<Character,
	// Integer>();

	/**
	 * Bit-arrays for letters
	 */
	ArrayList<BitSet> letterBitArrays;
	ArrayList<Long> params = new ArrayList<Long>();

	/**
	 * Bit-arrays for morphological markers
	 */
	ArrayList<BitSet> morphArray;

	int extraWords = 0;
	public SimpleTextDictionary()
	{

	}

	private void loadWordArrayFromZal(String fileName)
	{
		wordList = new ArrayList<String>();
		try
		{
			FileInputStream file = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(file);

			int cnt = 0;
			Scanner sc = new Scanner(bis);
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
		calcBitArrays();
	}

	public void loadDictionary()
	{
		loadWordsCompressed();
		loadBitVectors();
	}

	
	/**
	 * Process single word while loading from Zalyznyak dictionary
	 * @param cur - current index
	 */
	void buildSingleWordBitArray(int cur)
	{
		String word = wordList.get(cur);

		for (int i = 0; i < word.length(); i++)
		{
			char k = word.charAt(i);
			int listIndex = russianLetters.indexOf(k);

			if (listIndex == -1)
			{
				System.out.println("Bad symbol " + k);
				continue;
			}
			letterBitArrays.get(listIndex).set(cur);
		}

		for (int i = 0; i < MorphologicalFlags.ParArraySize; i++)
		{
			long fl = 1L << i;
			if ((params.get(cur) & fl) != 0)
				morphArray.get(i).set(cur);
		}
	}

	/**
	 * Create bit-arrays by word list
	 */
	void calcBitArrays()
	{
		System.out.println("Initializing bit arrays...");
		int singleArraySize = wordList.size() / 8;
		letterBitArrays = new ArrayList<BitSet>();
		for (int i = 0; i < russianLetters.length(); i++)
		{
			letterBitArrays.add(new BitSet(singleArraySize));

		}

		morphArray = new ArrayList<BitSet>();
		for (int i = 0; i < MorphologicalFlags.ParArraySize; i++)
		{
			morphArray.add(new BitSet(singleArraySize));
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
			letterBitArrays = new ArrayList<BitSet>();
			for (int i=0; i<russianLetters.length(); i++) 
			{
				File f = new File(String.format(LETTER_BIT_ARRAY_FILE_PATTERN, i));
				byte[] curLetterBitArray = new byte[(int) wordList.size() / 8 + 1];
				DataInputStream dis = new DataInputStream(new GZIPInputStream(new FileInputStream(f)));
				dis.readFully(curLetterBitArray);
				dis.close();
				letterBitArrays.add(BitSet.valueOf(curLetterBitArray));
				//while ()FileInputStream fis = new FileInputStream(new File(String.format(LETTER_BIT_ARRAY_FILE_PATTERN, i)));
			}
			
			morphArray = new ArrayList<BitSet>();
			for (int i=0; i<MorphologicalFlags.ParArraySize; i++) 
			{
				File f = new File(String.format(MORPH_BIT_ARRAY_FILE_PATTERN, i));
				byte[] curLetterBitArray = new byte[(int) wordList.size() / 8 + 1];
				DataInputStream dis = new DataInputStream(new GZIPInputStream(new FileInputStream(f)));
				dis.readFully(curLetterBitArray);
				dis.close();
				morphArray.add(BitSet.valueOf(curLetterBitArray));
				//while ()FileInputStream fis = new FileInputStream(new File(String.format(LETTER_BIT_ARRAY_FILE_PATTERN, i)));
			}
					
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void saveDictionary()
	{
		saveWordsCompressed();
		saveBitVectors();
	}
	
	private void loadWordsCompressed()
	{
		try 
		{
			wordList = new ArrayList<String>();
			long nanoBegin = System.nanoTime();
			System.out.println("Started loading compressed words");
			GZIPInputStream zis = new GZIPInputStream(new FileInputStream(new File("out/words.dat")));
			BufferedReader br = new BufferedReader(new InputStreamReader(zis));
			
			while (true)
			{
				String s = br.readLine();
				if (s==null) 
				{
					break;
				}
				wordList.add(s);
			}
			
			br.close();
			
			//Scanner s = new Scanner(zis);
			//while (s.hasNext()) {
			//	wordList.add(s.next());
			//}
			//s.close();
			
			long nanoEnd = System.nanoTime();
			System.out.println("Finished loading compressed words. time = " + (nanoEnd - nanoBegin));
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveWordsCompressed() 
	{
		try 
		{
			GZIPOutputStream zos = new GZIPOutputStream(new FileOutputStream(new File("out/words.dat")));
			PrintWriter pw = new PrintWriter(zos);
			for (String str : wordList)
				pw.println(str);
			zos.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void saveBitVectors()
	{
		try
		{
			for (int i = 0; i < letterBitArrays.size(); i++)
			{
				FileOutputStream f = new FileOutputStream(new File(String.format(LETTER_BIT_ARRAY_FILE_PATTERN, i)));
				GZIPOutputStream zos = new GZIPOutputStream(f);
				DataOutputStream dos = new DataOutputStream(zos);
				byte[] arr = letterBitArrays.get(i).toByteArray();
				dos.write(arr);
				/*for (int j = 0; j < arr.length; j++)
					f.write(arr[j]);*/
				dos.close();
				f.close();
			}
			for (int i = 0; i < morphArray.size(); i++)
			{
				FileOutputStream f = new FileOutputStream(new File(String.format(MORPH_BIT_ARRAY_FILE_PATTERN, i)));
				GZIPOutputStream zos = new GZIPOutputStream(f);
				DataOutputStream dos = new DataOutputStream(zos);
				byte[] arr = morphArray.get(i).toByteArray();
				dos.write(arr);
				dos.close();
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
	public List<String> getWordsWithLetters(String letters)
	{
		ArrayList<String> res = new ArrayList<String>();

		if (letters == null)
		{
			return res;
		}
		
		BitSet resultBitSet = new BitSet(wordList.size());
		resultBitSet.set(0, wordList.size());
		
		for (int i = 0; i < letters.length(); i++)
		{
			if (russianLetters.indexOf(letters.charAt(i)) == -1)
			{
				continue;
			}
			resultBitSet.and(letterBitArrays.get(russianLetters.indexOf(letters.charAt(i))));
		}

		for (int i = resultBitSet.nextSetBit(0); i >= 0; i = resultBitSet.nextSetBit(i+1)) 
		{
			res.add(wordList.get(i));
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

		BitSet resultBitSet = new BitSet(wordList.size());
		resultBitSet.set(0, wordList.size());
		
		List<Integer> columns = MorphologicalFlags.getIndexesForFlagSet(flags);

		for (int i = 0; i < columns.size(); i++)
		{
			resultBitSet.and(morphArray.get(columns.get(i)));
		}

		for (int i = resultBitSet.nextSetBit(0); i >= 0; i = resultBitSet.nextSetBit(i+1)) 
		{
			res.add(wordList.get(i));
        }
		return res;
	}
}
