import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Main console method that prints output from calculate function in
 * Frequencies.java
 * @author Laurence Rawlings
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        List<Map.Entry<String, Integer>> words =
                Frequencies.calculate(new SimpleCharacterReader());

        for(Entry<String, Integer> word : words){
            System.out.println(word.getKey() + " - " + word.getValue());
        }
    }
}
