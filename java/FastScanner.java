import java.io.*;

public class FastScanner implements AutoCloseable {

    private int pos = 0;
    private int len = 0;

    private char cash;
    private boolean cashed = false;

    private final StringBuilder curString;
    private final int bufSize = 4096;
    private final byte[] buf = new byte[bufSize];

    private final InputStream stream;

    FastScanner(InputStream stream) throws IOException {
        this.stream = stream;
        curString = new StringBuilder();
        check();
    }

    public void close() throws IOException {
        stream.close();
    }

    private void check() throws IOException {
        if (pos == len) {
            len = stream.read(buf);
            while (len == 0) {
                len = stream.read(buf);
            }
            pos = 0;
        }
    }

    private void incPos() throws IOException {
        pos++;
        cashed = false;
        check();
    }

    boolean hasNextLine() {
        return pos < len;
    }

    private int get(byte x, int ind) {
        return ((x >> ind) & 1);
    }

    private char nextChar() throws IOException {
        if (cashed) {
            return cash;
        }
        check();

        byte first = buf[pos];
        if (get(first, 7) == 1 && get(first, 6) == 1) {
            int cnt = 0;
            while (get(first, 7 - cnt) == 1) {
                buf[pos] ^= 1 << (7 - cnt);
                cnt++;
            }
            int res = 0;
            for (int i = 0; i < cnt; i++) {
                res = res * 64 + buf[pos];
                if (i + 1 < cnt) {
                    incPos();
                    buf[pos] ^= (1 << 7);
                }
            }
            cash = (char)res;
        } else {
            cash = (char)buf[pos];
        }
        cashed = true;
        return cash;
    }

    boolean hasNext() throws IOException {
        while (pos < len) {
            if (Character.isWhitespace(nextChar())) {
                incPos();
            } else {
                return true;
            }
        }
        return false;
    }

    boolean hasNextInLine() throws IOException {
        while (pos < len && notWord(nextChar())) {
            if (nextChar() == '\n') {
                incPos();
                return false;
            }
            incPos();
        }
        return pos < len;
    }

    String nextLine() throws IOException {
        while (pos < len) {
            if (nextChar() == '\n') {
                incPos();
                String res = curString.toString();
                curString.setLength(0);
                return res;
            } else {
                curString.append(nextChar());
                incPos();
            }
        }
        String res = curString.toString();
        curString.setLength(0);
        return res;
    }

    private String next() throws IOException {
        while (pos < len && Character.isWhitespace(nextChar())) {
            incPos();
        }
        while (pos < len) {
            if (Character.isWhitespace(nextChar())) {
                String res = curString.toString();
                curString.setLength(0);
                return res;
            } else {
                curString.append(nextChar());
                incPos();
            }
        }
        String res = curString.toString();
        curString.setLength(0);
        return res;
    }

    private boolean notWord(char c) {
        if (Character.isAlphabetic(c)) {
            return false;
        }
        if (c == '\'') {
            return false;
        }
        return Character.getType(c) != Character.DASH_PUNCTUATION;
    }

    String nextWord() throws IOException {
        while (pos < len && notWord(nextChar())) {
            incPos();
        }
        while (pos < len) {
            if (notWord(nextChar())) {
                String res = curString.toString();
                curString.setLength(0);
                return res;
            } else {
                curString.append(nextChar());
                incPos();
            }
        }
        String res = curString.toString();
        curString.setLength(0);
        return res;
    }

    int nextInt() throws IOException, NumberFormatException {
        return Integer.valueOf(next());
    }

}
