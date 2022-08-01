import java.util.*;
import java.io.*;

public class Madness {
    // declare public variables used in more than one class below
    public static int m;
    public static int n;
    public static int [][] graph;
    public static boolean [][] visited;
    public static PriorityQueue<Piles> toSee;
    public static int result;
    public static void main(String[] args) {
        FastIO io = new FastIO();
        m = io.nextInt();
        n = io.nextInt();
        // keep track of the height
        graph = new int [m][n];
        // keep track of whether each pile has been visited
        visited = new boolean [m][n];
        // prioirity queue to always return the lowest height difference
        toSee = new PriorityQueue<Piles>(new AscHeight());

        result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // initialise both height and status
                graph[i][j] = io.nextInt();
                visited[i][j] = false;
            }
        }

        // first pile to visit
        toSee.offer(new Piles(0, 0, 0));

        while (toSee.isEmpty() == false) {
            Piles pl = toSee.poll();
            // if already visited, skip
            if (visited[pl.x][pl.y] == true) {
                continue;
            } 
            // if reaches the end, done
            if (pl.x == m - 1 && pl.y == n - 1) {
                break;
            }
            // set status of visited to true
            visited[pl.x][pl.y] = true;

            // get the maximum between previous (previously max) and current height differences
            result = Math.max(result, pl.height);

            // examine four piles around the current pile
            Around(pl);
        }

        io.println(result);
        io.close();
    }
    
    // Piles class
    public static class Piles {
        public int x;
        public int y;
        public int height;

        public Piles(int one, int two, int three) {
            x = one;
            y = two;
            height = three;
        }
    }

    // comparator implemented in priority queue
    public static class AscHeight implements Comparator<Piles> {
        public int compare(Piles pile1, Piles pile2) {
            if (pile1.height < pile2.height) {
                return -1;
            } else if (pile1.height > pile2.height) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // method to examine four piles around the current pile
    public static void Around (Piles pl) {
        int xCor = pl.x;
        int yCor = pl.y;
        int theHeight = graph[xCor][yCor];

        if (xCor >= 1) {
            toSee.offer(new Piles(xCor-1, yCor, graph[xCor-1][yCor] - theHeight));
        }
        if (yCor >= 1) {
            toSee.offer(new Piles(xCor, yCor-1, graph[xCor][yCor-1] - theHeight));
        }
        if (xCor <= m-2) {
            toSee.offer(new Piles(xCor+1, yCor, graph[xCor+1][yCor] - theHeight));
        }
        if (yCor <= n-2) {
            toSee.offer(new Piles(xCor, yCor+1, graph[xCor][yCor+1] - theHeight));
        }
    }
}

// io class
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