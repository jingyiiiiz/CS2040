// Zhang Jingyi
// A0239855M

import java.util.*;
import java.io.*;

public class Teque {
    // initialise two halves - first and second, respectively
    private static int firstHead = 0;
    private static int firstTail = 0;
    private static int secondHead = 0;
    private static int secondTail = 0;
    private static HashMap<Integer, Integer> first = new HashMap<>();
    private static HashMap<Integer, Integer> second = new HashMap<>();

    public static void main(String[] args) {

        // handle inputs
        FastIO fio = new FastIO();
        int count = fio.nextInt();
        for (int i = 0; i < count; i++) {
            String command = fio.next();
            int num = fio.nextInt();

            // find conrresponding commands
            if (command.charAt(0) == 'g') {
                fio.println(get(num));
            } else if (command.charAt(5) == 'b') {
                pushBack(num);
            } else if (command.charAt(5) == 'f') {
                pushFront(num);
            } else if (command.charAt(5) == 'm') {
                pushMiddle(num);
            }
            }
            fio.close();
        }

    public static int get(int index) {
        // get in first half
        if (index < (firstTail - firstHead)) {
            return first.get(index + firstHead);
        } else {
        // get in second half
            index -= (firstTail - firstHead);
            return second.get(index + secondHead);
        }
    }

    public static void pushBack(int num) {
        second.put(secondTail, num);
        secondTail++;
        // adjust positions
        spaceOut();
    }

    public static void pushFront(int num) {
        firstHead--;
        first.put(firstHead, num);
        // adjust positions
        spaceOut();
    }
    

    public static void pushMiddle(int num) {
        if (first.size() == second.size()) {
            secondHead--;
            second.put(secondHead, num);
        } else {
            first.put(firstTail, second.get(secondHead));
            firstTail++;
            second.put(secondHead, num);
        }
    }

    public static void spaceOut() {
        if (first.size() < second.size() - 1) {
            first.put(firstTail, second.get(secondHead));
            firstTail++;
            second.remove(secondHead);
            secondHead++;
        } else if (first.size() > second.size()) {
            secondHead--;
            firstTail--;
            second.put(secondHead, first.get(firstTail));
            first.remove(firstTail);
        }
    }
}

class FastIO extends PrintWriter {
    BufferedReader br;
    StringTokenizer st;

    public FastIO() {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}