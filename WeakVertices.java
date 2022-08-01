import java.util.*;
import java.io.*;

public class WeakVertices {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();
        while (n != -1) {
            // set up graph
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
            // set up a reference
            int [] reference = new int[n];
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
                // zeros in reference indicating that the vertex is weak
                reference[i] = 0;
                for (int j = 0; j < n; j++) {
                    int num = io.getInt();
                    if (num == 1) {
                        adjList.get(i).add(j);
                    }
                }
            }

            for (int k = 0; k < n; k++) {
                for (int l = k + 1; l < n;l++) {
                    // for one edge
                    if (adjList.get(k).contains(l)) {
                        for (int m = l + 1; m < n; m++) {
                            // for the second edge
                            if (adjList.get(k).contains(m)) {
                                // if the third edge is also connected, a triangle is formed
                                if (adjList.get(m).contains(l)) {
                                    // indicate the vertex is not week in reference
                                    reference[k] = 1;
                                    reference[l] = 1;
                                    reference[m] = 1;
                                }
                            }
                        }
                    }
                }
            }

            for (int o = 0; o < n; o++) {
                if (reference[o] == 0) {
                    System.out.println(o);
                }
            }

            n = io.getInt();
        }

        return;
    }
}

// io class
class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
