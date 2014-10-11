package Duplicate;

import java.io.*;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class InputReader {
    BufferedReader in;
    StringTokenizer st;

    public InputReader(InputStream stream) {
        in = new BufferedReader(new InputStreamReader(stream));
        eat("");
    }

    public String next() {
        while (!st.hasMoreTokens())
            eat(nextLine());
        return st.nextToken();
    }

    public String nextLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new InputMismatchException();
        }
    }

    public void eat(String str) {
        if (str == null) throw new InputMismatchException();
        st = new StringTokenizer(str, ".,!?:;-\n\r() ");
    }
}