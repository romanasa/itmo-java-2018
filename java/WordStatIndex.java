import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatIndex {
    public static void main(String[] args) {

        try (FastScanner scanner = new FastScanner(new FileInputStream(args[0]))) {

            HashMap<String, ArrayList<Integer>> ind = new HashMap<>();
            ArrayList<String> order = new ArrayList<>();

            int num = 0;
            while (scanner.hasNextLine()) {

                while (scanner.hasNextInLine()) {
                    String word = scanner.nextWord().toLowerCase();
                    num++;
                    if (!ind.containsKey(word)) {
                        ind.put(word, new ArrayList<>());
                        order.add(word);
                    }
                    ind.get(word).add(num);
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                for (String word : order) {
                    writer.write(word + " " + ind.get(word).size());
                    for (Integer index : ind.get(word)) {
                        writer.write(" " + index);
                    }
                    writer.newLine();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Usage: java WordStatIndex <input> <output>");
            } catch (UnsupportedEncodingException e) {
                System.out.println("Unsupported Encoding");
            } catch (IOException e) {
                System.out.println(e.toString());
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java WordStatIndex <input> <output>");
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + args[0]);
        } catch (IOException e) {
            System.out.println("Error in reading");
            System.out.println(e.toString());
        }

    }
}
