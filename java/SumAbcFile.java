import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.*;

public class SumAbcFile {
    public static void main(String[] args) {
        int result = 0;
        boolean needWrite = true;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            String line;
            label: while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\p{javaWhitespace}+");
                for (int i = (words.length > 0 && words[0].isEmpty() ? 1 : 0); i < words.length; i++) {
                    String word = words[i].toLowerCase();
                    StringBuilder num = new StringBuilder();
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) == '-' || word.charAt(j) == '+') {
                            num.append(word.charAt(j));
                        } else if (('a' <= word.charAt(j) && word.charAt(j) <= 'j')) {
                            num.append((char) (word.charAt(j) - 'a' + '0'));
                        } else {
                            System.out.println("Invalid value: " + word);
                            needWrite = false;
                            break label;
                        }
                    }
                    try {
                        result += Integer.parseInt(num.toString());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid value: " + word);
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

