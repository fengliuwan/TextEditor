package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		if(wordList.size() != 0) return;
		String[] words = sourceText.split("[\\s]+");
		starter = words[0];
		String prevWord = words[0];
		for(int i = 1; i < words.length; i++) {
			String w = words[i];
			ListNode lnFound = findListNode(prevWord, wordList);
				if(lnFound != null) {
					lnFound.addNextWord(w);
				}
				else {
					ListNode newLN = new ListNode(prevWord);
					newLN.addNextWord(w);
					wordList.add(newLN);
				}
			prevWord = w;
		}
		ListNode lastWordNode = findListNode(prevWord, wordList);
			if(lastWordNode != null) {
				lastWordNode.addNextWord(starter);
			} 
			else {
				ListNode newLN = new ListNode(prevWord);
				newLN.addNextWord(starter);
				wordList.add(newLN);
		}
	}
	
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if(numWords <= 0 || wordList.isEmpty()) {
			return"";
		}
		String currWord = starter;
		StringBuilder output = new StringBuilder();
		output.append(currWord);
		for(int i = 0; i < numWords - 1; i++) {
			ListNode ln = findListNode(currWord, wordList);
			String w = ln.getRandomNextWord(rnGenerator);
			output.append(" " + w);
			currWord = w;
		}
		return output.toString();
	}
	
	
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList.clear();
		train(sourceText);
		
	}
	
	private ListNode findListNode(String word, List<ListNode> list) {
		for(ListNode LN : list) {
			if(LN.getWord().equals(word)) {
				return LN;
			}
		}
		return null;
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL mygen = new MarkovTextGeneratorLoL(new Random(10));
		mygen.train("hi there hi Leo hi James hi Joe hello there fun there sunny there great");
		System.out.println(mygen);
		System.out.println(mygen.generateText(30));
		
		
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int ranNum = generator.nextInt(nextWords.size());
	    return nextWords.get(ranNum);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


