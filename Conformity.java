import java.io.*;
import java.util.*;

public class Conformity {
    public static void main(String[] args) throws IOException {
        // get all inputs
        Kattio io = new Kattio(System.in);
        int total = io.getInt();
        int [] eachStudent = new int[5];
        HashMap<String,Integer> files = new HashMap<String,Integer>();

        for (int i = 0; i < total; i++) {
            String toAdd = "";

            for (int j = 0; j < 5; j++) {
                eachStudent[j] = io.getInt();
            }
            
            // sorting values to ensure each unique combination is properly matched
            Arrays.sort(eachStudent);

            for (int k = 0; k < 5; k++) {
                // string concatenation
                toAdd += eachStudent[k];
            }

            if (files.get(toAdd) == null) {
                // put inside the hashmap
                files.put(toAdd, 1);
            } else {
                // if already in hashmap, increase counter by 1
                int current = files.get(toAdd);
                files.replace(toAdd, current + 1);
            }
        }

        // get all values
        List<Integer> all = new ArrayList<>(files.values());
        Collections.sort(all);
        // get maximum value
        int maxVal = all.get(all.size() - 1);

        int result = 0;

        for (int l = 0; l < all.size(); l++) {
            if (all.get(l) == maxVal) {
                // handle equal maximums
                result += maxVal;
            }
        }

        System.out.println(result);
    }
}

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
