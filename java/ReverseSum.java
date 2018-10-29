import java.io.IOException;
import java.util.*;

public class ReverseSum {

    public static void main(String[] args) {
        Vector<Vector<Integer>> words = new Vector<>();
        long st = System.nanoTime();

        FastScanner scanner = null;
        try {
            scanner = new FastScanner(System.in);

            int lineInd = 0;
            int len = 0;
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
                len = Math.max(len, words.get(lineInd).size());
                lineInd++;
            }

            if (need) {
            
          		int[] sumR = new int[words.size()];
          		int[] sumC = new int[len];
          		Arrays.fill(sumR, 0);
          		Arrays.fill(sumC, 0);
          		
          		for (int i = 0; i < words.size(); i++) {
          			for (int j = 0; j < words.get(i).size(); j++) {
          				int x = words.get(i).get(j);
          				sumR[i] += x;
          				sumC[j] += x;
          			}
          		}
          		
          		for (int i = 0; i < words.size(); i++) {
          			for (int j = 0; j < words.get(i).size(); j++) {
          				System.out.print((sumR[i] + sumC[j] - words.get(i).get(j)) + " ");
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
