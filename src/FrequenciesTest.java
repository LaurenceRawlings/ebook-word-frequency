import org.junit.jupiter.api.Test;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests Frequencies class.
 * @author Laurence Rawlings
 * @version 1.0
 */
class FrequenciesTest {
    private final SimpleCharacterReader READER_TEST =
            new SimpleCharacterReader();

    private final TestCharacterReader READER_TEST_EMPTY =
            new TestCharacterReader();

    @Test
    void calculate() {
        assertEquals(85, Frequencies.calculate(READER_TEST).size());
        assertNull(Frequencies.calculate(null));
        assertEquals(new ArrayList<Map.Entry<String, Integer>>(),
                Frequencies.calculate(READER_TEST_EMPTY));
    }

    public class TestCharacterReader implements ICharacterReader {
        private int m_Pos = 0;

        public static final char lf = '\n';

        private String m_Content = "";

        public char GetNextChar() throws EOFException {
            if (m_Pos >= m_Content.length()) {
                throw new EOFException();
            }

            return m_Content.charAt(m_Pos++);
        }

        public void Dispose() {
            // Do nothing
        }
    }
}