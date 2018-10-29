import java.io.*;

public class FastScanner {

    private int pos = 0;
    private int len = 0;
    private StringBuilder curString;
    private final int bufSize = 4096;
    private char[] buf = new char[bufSize];

    private Reader reader;

    FastScanner() {
        reader = null;
        curString = null;
    }

    FastScanner(String text) throws IOException {
        reader = new StringReader(text);
        curString = new StringBuilder();
        check();
    }

    FastScanner(InputStream stream) throws IOException {
        reader = new InputStreamReader(stream);
        curString = new StringBuilder();
        check();
    }

    void close() throws IOException {
        reader.close();
    }

    private void check() throws IOException {
        if (pos == len) {
            len = reader.read(buf, 0, bufSize);
            while (len == 0) {
                len = reader.read(buf, 0, bufSize);
            }
            pos = 0;
        }
    }

    private void incPos() throws IOException {
        pos++;
        check();
    }

    boolean hasNextLine() {
        return pos < len;
    }

    boolean hasNext() throws IOException {
        while (pos < len) {
            if (Character.isWhitespace(buf[pos])) {
                incPos();
            } else {
                return true;
            }
        }
        return false;
    }

    boolean hasNextInLine() throws IOException {
        while (pos < len && Character.isWhitespace(buf[pos])) {
            if (buf[pos] == '\n') {
                incPos();
                return false;
            }
            incPos();
        }
        return pos < len;
    }

    String nextLine() throws IOException {
        while (pos < len) {
            if (buf[pos] == '\n') {
                incPos();
                String res = curString.toString();
                curString.setLength(0);
                return res;
            } else {
                curString.append(buf[pos]);
                incPos();
            }
        }
        String res = curString.toString();
        curString.setLength(0);
        return res;
    }

    String next() throws IOException {
        while (pos < len && Character.isWhitespace(buf[pos])) {
            incPos();
        }
        while (pos < len) {
            if (Character.isWhitespace(buf[pos])) {
                String res = curString.toString();
                curString.setLength(0);
                return res;
            } else {
                curString.append(buf[pos]);
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
