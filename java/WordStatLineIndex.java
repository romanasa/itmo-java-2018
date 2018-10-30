import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatLineIndex {
    public static void main(String[] args) {

        try (FastScanner scanner = new FastScanner(new FileInputStream(args[0]))) {

            HashMap<String, ArrayList<Integer>> line = new HashMap<>();
            TreeMap<String, ArrayList<Integer>> ind = new TreeMap<>();

            int lineInd = 0;
            while (scanner.hasNextLine()) {

                int num = 0;
                lineInd++;
                while (scanner.hasNextInLine()) {
                    String word = scanner.nextWord().toLowerCase();
                    num++;
                    ind.putIfAbsent(word, new ArrayList<>());
                    line.putIfAbsent(word, new ArrayList<>());
                    ind.get(word).add(num);
                    line.get(word).add(lineInd);
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {

                for (String word : ind.keySet()) {
                    writer.write(word + " " + ind.get(word).size());
                    for (int i = 0; i < (int) ind.get(word).size(); i++) {
                        writer.write(" " + line.get(word).get(i) + ":" + ind.get(word).get(i));
                    }
                    writer.newLine();
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Usage: java WordStatInput <input> <output>");
            } catch (IOException e) {
                System.out.println(e.toString());
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java WordStatInput <input> <output>");
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + args[0]);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding");
        } catch (IOException e) {
            System.out.println("Error in reading");
            System.out.println(e.toString());
        }

    }
}
