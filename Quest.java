// Zhang Jingyi
// A0239855M

import java.io.*;
import java.util.*;

public class Quest {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in);
        int total = io.getInt();
        TreeSet<Energy> kattis = new TreeSet<>(new Storage());
        long newId = 0;

        for (int i = 0; i < total; i++) {
            String command = io.getWord();

            if (command.charAt(0) == 'a') {
                long e = io.getLong();
                long g = io.getLong();
                newId++;
                kattis.add(new Energy(e, g, newId));
            } else if (command.charAt(0) == 'q') {
                long x = io.getLong();
                long result = 0;
                Energy toCompare = kattis.floor(new Energy(x, 1000000, 1000000));

                while (toCompare != null && x - toCompare.getCon() >= 0) {
                    kattis.remove(toCompare);
                    result += toCompare.getRe();
                    x -= toCompare.getCon();
                    toCompare = kattis.floor(new Energy(x, 1000000, 1000000));
                }

                System.out.println(result);
            }
        }
    }
}

class Energy {
    public long consumption;
    public long reward;
    public long id;

    public Energy (long consumption, long reward, long id) {
        this.consumption = consumption;
        this.reward = reward;
        this.id = id;
    }

    public long getCon() {
        return this.consumption;
    } 

    public long getRe() {
        return this.reward;
    }

    public long getId() {
        return this.id;
    }
}

class Storage implements Comparator<Energy> {
    @Override
    public int compare(Energy energy1, Energy energy2) {
        if (energy1.getCon() > energy2.getCon()) {
            return 1;
        } else if (energy1.getCon() < energy2.getCon()) {
            return -1;
        } 
        // if same energy consumption, compare rewards
        else if (energy1.getRe() > energy2.getRe()) {
            return 1;
        } else if (energy1.getRe() < energy2.getRe()) {
            return -1;
        } 
        // if same rewards, compare id, always put largest id first, so that later with 100000 gold the current entries will always have fewer rewards than the query to be compared
        else if (energy1.getId() > energy2.getId()) {
            return 1;
        } else if (energy1.getId() < energy2.getId()){
            return -1;
        } else {
            return 0;
        }
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

