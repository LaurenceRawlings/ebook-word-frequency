import java.io.EOFException;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author Laurence Rawlings
 * @version 1.0
 */
public class Frequencies {
    /**
     * Calculates and returns a list containing key value pais of words and
     * their frequencies from an input string.
     * @param reader the reader that will be used to calculate word
     *               frequencies from.
     * @return the sorted list of word frequencies. Sorted in descending order
     *              of word frequency, then alphabetically if word
     *              frequencies are the same. Key = Word, Value = frequency.
     */
    public static List<Map.Entry<String, Integer>> calculate(ICharacterReader reader) {
        if (reader == null) {
            return null;
        }
        HashMap<String, Integer> words = new HashMap<>();

        try {
            StringBuilder buffer = new StringBuilder();
            //Exception-controlled loop because EOFException will be thrown when
            // end of input string is reached.
            while (true) {
                char next = reader.GetNextChar();
                if(Character.isLetter(next)) {
                    //Build up a word in the buffer until the end of the word is
                    // reached.
                    buffer.append(next);
                } else {
                    //If the buffer is not empty check the word and increase
                    // it's frequency by 1.
                    if (buffer.length() >  0) {
                        //Convert to lowercase so for example It and it will
                        // be stored as the same word.
                        String word = buffer.toString().toLowerCase();
                        Integer current = words.get(word);
                        words.put(word, current == null ? 1 : ++current);
                        //Reset the buffer.
                        buffer = new StringBuilder();
                    }
                }
            }
        } catch (EOFException ignored) {
            //When end of the input string is reached dispose of the reader.
            reader.Dispose();
        }

        //Define the comparator used to sort the key value pairs from the
        // words hash map. Sorts in descending order of frequency, then
        // alphabetically if word frequencies are the same.
        Comparator<Entry<String, Integer>> valueComparator =
                (word1, word2) -> {
                    int frequencyCompare =
                            word2.getValue().compareTo(word1.getValue());
                    if (frequencyCompare == 0) {
                        return word1.getKey().compareTo(word2.getKey());
                    } else {
                        return frequencyCompare;
                    }
                };

        //Get and store the entry set from the word hash map.
        List<Entry<String, Integer>> sortedWords = new ArrayList<>(words.entrySet());

        //Sort the word list using the defined comparator.
        sortedWords.sort(valueComparator);

        return sortedWords;
    }
}
