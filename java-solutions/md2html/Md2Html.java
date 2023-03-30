package md2html;

import md2htmlMarkup.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    public static void main(String[] args) {
        String text = readFile(args[0]);
        BlockCreator creator = new BlockCreator(text);
        List<String> blocks = creator.divideByBlocks();
        List<PrimePart> parts = new ArrayList<>();
        for (String block : blocks) {
            PrimePartCreator creatorPrime = new PrimePartCreator(block);
            parts.add(creatorPrime.createPart());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            parts.get(i).toHtml(sb);
            if (i < parts.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        writeToFile(args[1], sb.toString());
    }

    public static String readFile(String nameOfFile) {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(nameOfFile), StandardCharsets.UTF_8
            )
        )) {
            StringBuilder text = new StringBuilder();
            int read = reader.read();
            while (read != -1) {
                text.append((char) read);
                read = reader.read();
            }
            return text.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException file not found: " + e.getMessage());
        }
        return null;
    }

    public static void writeToFile(String nameOfFile, String text) {
        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(nameOfFile), StandardCharsets.UTF_8
            )
        )) {
            writer.write(text);
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException file not found: " + e.getMessage());
        }
    }
}
