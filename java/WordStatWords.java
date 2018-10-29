import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatWords {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {

            HashMap<String, Integer> cnt = new HashMap<>();
            ArrayList<String> order = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                String words[] = line.split("[^'\\p{javaLetter}\\p{Pd}]+");
                for (int i = (words.length > 0 && words[0].isEmpty() ? 1 : 0); i < words.length; i++) {
                    String word = words[i];
                    if (cnt.containsKey(word)) {
                        cnt.replace(word, cnt.get(word) + 1);
                    } else {
                        cnt.put(word, 1);
                        order.add(word);
                    }
                }
            }

            Collections.sort(order);
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                for (String word : order) {
                    writer.write(word + " " + String.valueOf(cnt.get(word)));
                    writer.newLine();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Usage: java WordStatInput <input> <output>");
            } catch (IOException e) {
                System.out.println("Something wrong with writing");
                System.out.println(e.toString());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java WordStatInput <input> <output>");
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + args[0]);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding");
        } catch (IOException e) {
            System.out.println("Something wrong with reading");
            System.out.println(e.toString());
        }
    }
}
