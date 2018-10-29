import java.io.IOException;
import java.util.*;

public class Reverse {

    public static void main(String[] args) {
        Vector<Vector<Integer>> words = new Vector<>();
        long st = System.nanoTime();

        FastScanner scanner = null;
        try {
            scanner = new FastScanner(System.in);

            int lineInd = 0;
            boolean need = true;
            label: while (scanner.hasNextLine()) {
                words.add(new Vector<>());

                while (scanner.hasNextInLine()) {
                    try {
                        words.get(lineInd).add(scanner.nextInt());
                    } catch (IOException e) {
                        System.out.println("Something wrong with reading in line");
                        System.out.println(e.toString());
                        need = false;
                        break label;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid value:");
                        System.out.println(e.toString());
                        need = false;
                        break label;
                    }
                }
                lineInd++;
            }

            if (need) {
                Collections.reverse(words);
                for (Vector<Integer> word : words) {
                    Collections.reverse(word);
                    for (Integer aWord : word) {
                        System.out.print(aWord + " ");
                    }
                    System.out.println();
                }
            }

        } catch (IOException e) {
            System.out.println("Something wrong with reading");
            System.out.println(e.toString());
        } finally {
            try {
                if (scanner != null) {
                    scanner.close();
                }
            } catch (IOException e) {
                System.out.println("Error in closing scanner");
                System.out.println(e.toString());
            }
        }
        long fn = System.nanoTime();
        System.err.println("time: " + (fn - st) / 1e9);
    }
}
