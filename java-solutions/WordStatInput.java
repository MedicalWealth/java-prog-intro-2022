import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import java.util.Map;
public class WordStatInput {
    public static void main(String[] args) {
        final byte[] TYPES = new byte[]{
            Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER, 
                Character.TITLECASE_LETTER, Character.MODIFIER_LETTER,
            Character.OTHER_LETTER, Character.DASH_PUNCTUATION
        };
        Map<String, Integer> wordStat = new LinkedHashMap<>();
        try {
            BufferedScanner reader = new BufferedScanner(
                new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8), 
                    TYPES, new char[]{'\''}
            );
            try {
                BufferedScanner.TypeOfToken line = reader.whatIsNext();
                while (line != BufferedScanner.TypeOfToken.NOTHING) {
                    if (line == BufferedScanner.TypeOfToken.TOKEN) {
                        String word = reader.getNext().toLowerCase();
                        wordStat.put(word, wordStat.getOrDefault(word, 0) + 1); 
                    }
                    line = reader.whatIsNext();
                }
            } finally {
                reader.close();
            }
            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1])
                    )
            );
            try {
                for (Entry<String, Integer> entry: wordStat.entrySet()) {
                    writer.write(entry.getKey() + " " + entry.getValue() + System.lineSeparator());
                }
            } finally {
                writer.close();
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
