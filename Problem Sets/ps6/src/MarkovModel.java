import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This is the main class for your Markov Model.
 *
 */
public class MarkovModel {

	// Use this to generate random numbers as needed
	private Random generator = new Random();

	// This is a special symbol to indicate no character
	private Map<String, Map<String, Integer>> model;
	int order;

	/**
	 * Constructor for MarkovModel class.
	 *
	 * @param order the number of words to identify for the Markov Model sequence
	 * @param seed the seed used by the random number generator
	 */
	public MarkovModel(int order, long seed) {
		// Initialize your class here
		model = new HashMap<>();
		this.order = order;
		// Initialize the random number generator
		generator.setSeed(seed);
	}

	/*
	 * Helper method to get kgram as a string
	 */
	String getKeyPhrase(String[] words, StringBuilder sb, int order, int index) {
		for (int j = index; j < index + order && j < words.length; j++) {
			sb.append(words[j]).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * Builds the Markov Model based on the specified text string.
	 */
	public void initializeText(String text) {
		// Build the Markov model here
		String[] words = text.split("\\s+");
		for (int i = 0; i + order < words.length; i++) {
			String key = getKeyPhrase(words, new StringBuilder(), order, i);
			if(!model.containsKey(key)) {
				//create new map of words that is after key phrase/word
				Map<String, Integer> freq = new HashMap<>();
				//check if current word is not empty, increment freq
				if(i + order < words.length && !(words[i+order].isEmpty()))
					freq.put(words[i+order], 1);
				//create new entry into hash table
				model.put(key, freq);
			} else {
				//if model contains key phrase/entry, increment frequency
				Map<String, Integer> freq = model.get(key);
				if(i + order < words.length && !(words[i+order].isEmpty())) {
					String nextWord = words[i+order];
					freq.put(nextWord, freq.getOrDefault(nextWord, 0) + 1);
				}

			}
		}
	}

	/**
	 * Returns the number of times the specified kgram appeared in the text.
	 */
	public int getFrequency(String kgram) {
		int wordLength = kgram.split("\\s+").length;
		if(wordLength != order || model.get(kgram) == null)
			return 0;
		else if (model.containsKey(kgram)) {
			int result = 0;
			//gets array that matches kgram, returns the total count of the kgram that has appeared
			for(int wordCount : model.get(kgram).values()){
				result += wordCount;
			}
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * Returns the number of times the word w appears immediately after the specified kgram.
	 */
	public int getFrequency(String kgram, String w) {
		int wordLength = kgram.split("\\s+").length;
		if(wordLength != order || model.get(kgram) == null)
			return 0;
		else {
			//returns corresponding frequency of w appearing
			return model.get(kgram).get(w);
		}
	}

	/**
	 * Generates the next word from the Markov Model.
	 * Return null if the kgram is not in the table, or if there is no
	 * valid word following the kgram.
	 */
	public String nextCharacter(String kgram) {
		// See the problem set description for details
		// on how to make the random selection.
		int wordLength = kgram.split("\\s+").length;
		if(wordLength != order || !model.containsKey(kgram))
			return null;
		int freq = getFrequency(kgram); //returns number of times key appears
		int randomNo = generator.nextInt(freq); // generate random no. from 1 to value of freq
		int sum = 0;
		//for every subsequent entry that is after kgram, add into word.
		for(Map.Entry<String, Integer> wordList : model.get(kgram).entrySet()) {
			String word = wordList.getKey();
			int wordCount = wordList.getValue();
			if(wordCount > 0) {
				sum += wordCount;
				if(randomNo < sum)
					return " " + word;
			}
		}

		return null;
	}
}
