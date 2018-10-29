import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.*;

public class SumFile {
    public static void main(String[] args) {
        int result = 0;
        boolean needWrite = true;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            String line;
            label: while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\p{javaWhitespace}+");
                for (int i = (words.length > 0 && words[0].isEmpty() ? 1 : 0); i < words.length; i++) {
                    String word = words[i];
                    try {
                        result += Integer.parseInt(word);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect value");
                        needWrite = false;
                        break label;
                    }
                }
            }
            if (needWrite) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                    writer.write(String.valueOf(result));
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println("Something wrong with writing");
                    System.out.println(e.toString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Usage: java SumFile <input> <output>");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + args[0]);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding");
        } catch (IOException e) {
            System.out.println("Something wrong with reading");
            System.out.println(e.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java SumFile <input> <output>");
        }
    }
}

