package search;

public class BinarySearchSpan {

    // a exists
    // imagine: a[-1] = +inf, a[a.length] = -inf
    // for all i = -1..a.length - 1: a[i] >= a[i + 1]
    public static int iterativeBinarySearch(int x, int[] a) {
        int l = -1, r = a.length;

        // r - l >= 1
        // inv I: a[l] >= x > a[r]
        // r' - l' < r - l
        while (r - l > 1) {

            int m = (l + r) / 2;
            // l < m < r
            // 0 <= m <= a.length - 1 => a[m] exists
            if (a[m] < x) {
                //I
                r = m;
                //x > a[m] => a[l] >= x > a[m]
                //l = l' && r' < r => r' - l' < r - l
            } else {
                //I
                l = m;
                //a[m] >= x => a[m] >= x > a[r]
                //r = r' && l' > l => r' - l' < r - l
            }
        }
        return l;
    }
    //a[l] >= x && a[l + 1] < x && a[i]' = a[i]

    //a exists
    //imagine: a[-1] = +inf, a[a.length] = -inf
    //for all i = -1..a.length - 1: a[i] >= a[i + 1]
    //a[l] > x >= a[r]
    //-1 <= l < r <= a.length
    public static int recursiveBinarySearch(int x, int l, int r, int[] a) {
        if (l + 1 == r) {
            return r;
        }
        //r - l > 1
        int m = (l + r) / 2;
        //l < m < r
        //-1 < m < a.length => a[m] exists
        if (a[m] <= x) {
            //x >= a[m] => a[l] > x >= a[m]
            //l' = l && r' = m < r => r' - l' < r - l
            return recursiveBinarySearch(x, l, m, a);
        } else {
            //a[m] > x => a[m] > x >= a[r]
            //l' > l && r' = r => r' - l' < r - l
            return recursiveBinarySearch(x, m, r, a);
        }
    }
    //a[R - 1] > x && a[R] <= x && a[i]' = a[i]

    public static void main(String[] args) {
        int x = Integer.valueOf(args[0]);
        int n = args.length - 1;

        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = Integer.valueOf(args[i]);
        }

        int rightpos = iterativeBinarySearch(x, a);
        int leftpos = recursiveBinarySearch(x, -1, a.length, a);

        if (leftpos > rightpos) {
            System.out.println(leftpos + " " + 0);
        } else {
            System.out.println(leftpos + " " + (rightpos - leftpos + 1));
        }
    }
}
