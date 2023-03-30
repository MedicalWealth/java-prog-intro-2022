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

public class WsppLastL {
    public static void main(String[] args) {
        final byte[] TYPES = new byte[]{
            Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER,
            Character.TITLECASE_LETTER, Character.MODIFIER_LETTER,
            Character.OTHER_LETTER, Character.DASH_PUNCTUATION
        };
        Map<String, FrequencyOfWord> wordStat = new LinkedHashMap<>();
        try {
            try (BufferedScanner reader = new BufferedScanner(
                new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8),
                TYPES, new char[]{'\''}
            )) {
                BufferedScanner.TypeOfToken line = reader.whatIsNext();
                Map<String, Integer> wordStatInLine = new LinkedHashMap<>();
                int countInLine = 0;
                while (line != BufferedScanner.TypeOfToken.NOTHING) {
                    if (line == BufferedScanner.TypeOfToken.NEW_LINE) {
                        for (Entry<String, Integer> entry : wordStatInLine.entrySet()) {
                            wordStat.get(entry.getKey()).list.add(entry.getValue());
                        }
                        wordStatInLine.clear();
                        countInLine = 0;
                    } else {
                        String word = reader.getNext().toLowerCase();
                        countInLine++;
                        wordStatInLine.put(word, countInLine);
                        wordStat.put(word, wordStat.getOrDefault(word, new FrequencyOfWord()));
                        wordStat.get(word).countOccurrences++;
                    }
                    line = reader.whatIsNext();
                }
            }

            try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]), StandardCharsets.UTF_8
                )
            )) {
                for (Entry<String, FrequencyOfWord> entry : wordStat.entrySet()) {
                    writer.write(entry.getKey() + " " + entry.getValue().countOccurrences);
                    for (int i = 0; i < entry.getValue().list.size(); i++) {
                        writer.write(" " + entry.getValue().list.get(i));
                    }
                    writer.write(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O exception: " + e.getMessage());
        }
    }

    static class FrequencyOfWord {
        public IntList list;
        public int countOccurrences;

        FrequencyOfWord() {
            this.list = new IntList();
            this.countOccurrences = 0;
        }
    }
}