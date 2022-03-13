package spelling;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class NearbyWordsTester {
	private Dictionary d;
	
	@Before
	public void  setup() throws Exception {
		d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
	}
	
	@Test
	public void testInsertion() {
		String s = "ea";
		List<String> newList = new ArrayList<String>();
		List<String> wordList = new ArrayList<String>();
		NearbyWords nw = new NearbyWords(d);
		nw.insertions(s, newList, false);
		System.out.println(newList);
		
		nw.insertions(s, wordList, true);
		System.out.println(wordList);
	}
	
	@Test
	public void testDeletion() {
		String s = "good";
		List<String> newList = new ArrayList<String>();
		List<String> wordList = new ArrayList<String>();
		NearbyWords nw = new NearbyWords(d);
		nw.deletions(s, newList, false);
		System.out.println(newList);
		
		nw.deletions(s, wordList, true);
		System.out.println(wordList);
	}

}
