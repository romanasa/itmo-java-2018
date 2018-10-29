import java.lang.*;
import java.util.*;

public class ReverseMax {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<String[]> lines = new ArrayList<>();

        int len = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            line += " ";
            lines.add(line.split(" ", 0));
            len = Math.max(len, lines.get(lines.size() - 1).length);
        }

        int[] maxR = new int[lines.size()];
        int[] maxC = new int[len];
        Arrays.fill(maxR, Integer.MIN_VALUE);
        Arrays.fill(maxC, Integer.MIN_VALUE);

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length; j++) {
                int x = Integer.parseInt(lines.get(i)[j]);
                maxR[i] = Math.max(maxR[i], x);
                maxC[j] = Math.max(maxC[j], x);
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length; j++) {
                System.out.print(Math.max(maxR[i], maxC[j]) + " ");
            }
            System.out.println();
        }
    }
}