import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Wspp {
    public static void main(String[] args) {
        final byte[] TYPES = new byte[]{
            Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER, 
                Character.TITLECASE_LETTER, Character.MODIFIER_LETTER,
            Character.OTHER_LETTER, Character.DASH_PUNCTUATION
        };
        Map<String, IntList> wordStat = new LinkedHashMap<>();
        try {
            BufferedScanner reader = new BufferedScanner(
                new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8), 
                    TYPES, new char[]{'\''}
            );
            try {
                int currentWord = 0;
                BufferedScanner.TypeOfToken line = reader.whatIsNext();
                while (line != BufferedScanner.TypeOfToken.NOTHING) {
                    if (line == BufferedScanner.TypeOfToken.TOKEN) {
                        currentWord++;
                        String word = reader.getNext().toLowerCase();
                        if (wordStat.containsKey(word)) {
                            wordStat.get(word).add(currentWord);
                        } else {
                            wordStat.put(word, new IntList());
                            wordStat.get(word).add(currentWord);
                        }
                    }
                    line = reader.whatIsNext();
                }
            } finally {
                reader.close();
            }
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]), StandardCharsets.UTF_8
                )
            );
            try {
                for (Entry<String, IntList> entry: wordStat.entrySet()) {
                    writer.write(entry.getKey() + " " + entry.getValue().size());
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        writer.write(" " + entry.getValue().get(i));
                    }
                    writer.write(System.lineSeparator());
                }
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O exception: " + e.getMessage());
        }
    }
}