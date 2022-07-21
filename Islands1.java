// Zhang Jingyi
// A0239855M

import java.util.*;
import java.io.*;

public class Islands1 {
    // declare public variables used in more than one class below
    public static int r;
    public static int c;
    public static int [][] map;
    public static boolean [][] visited;
    public static void main(String[] args) {
        FastIO io = new FastIO();
        r = io.nextInt();
        c = io.nextInt();
        // keep track of the characters
        map = new int [r][c];
        // keep track of whether each point has been visited
        visited = new boolean[r][c];
        Queue<Points> occupied = new ArrayDeque<Points>();
        // Referred to https://stackoverflow.com/questions/4626812/how-do-i-instantiate-a-queue-object-in-java

        int result = 0;

        for (int i = 0; i < r; i++) {
            String raw = io.next();
            for (int j = 0; j < c; j++) {
                // initialise both map and visited
                visited[i][j] = false;
                // if island, add to occupied to keep track of all islands
                if (raw.charAt(j) == 'L') {
                    occupied.offer(new Points(i, j));
                    map[i][j] = 1;
                } else if (raw.charAt(j) == 'C') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = 2;
                }
                
                
                
            }
        }
        
        while (occupied.isEmpty() == false) {
            Points pt = occupied.poll();
            // if already visited, means has been taken care of under other "islands", skip, no need to count for it
            if (visited[pt.x][pt.y] == true) {
                continue;
            }
            // if not visited, perform BFS on it to find all "connected" islands
            BFS(pt);
            // also, if not visited, increment number of "connected components" by one
            result++;
        }


        io.println(result);
        io.close();
    }

    // Points class
    public static class Points {
        public int x;
        public int y;
    
        public Points(int first, int second) {
            x = first;
            y = second;
        }
    }    

    // BFS performed on a specific point
    public static void BFS (Points pt) {
        Queue<Points> around = new ArrayDeque<Points>();
        around.offer(pt);
        while (around.isEmpty() == false) {
            Points thisPt = around.poll();
            int xCor = thisPt.x;
            int yCor = thisPt.y;

            // if already visited, pass
            if (visited[thisPt.x][thisPt.y] == true) {
                continue;
            } 
            // if havent, set it to visited
            else {
                visited[thisPt.x][thisPt.y] = true;
            }

            // up
            if (xCor >= 1) {
                if (map[xCor-1][yCor] == 1 || map[xCor-1][yCor] == 0) {
                    around.offer(new Points(xCor-1, yCor));
                }
            }
            // right
            if (yCor >= 1) {
                if (map[xCor][yCor-1] == 1 || map[xCor][yCor-1] == 0) {
                    around.offer(new Points(xCor, yCor-1));
                }
            }
            // down
            if (xCor <= r-2) {
                if (map[xCor+1][yCor] == 1 || map[xCor+1][yCor] == 0) {
                    around.offer(new Points(xCor+1, yCor));
                }
            }
            // right
            if (yCor <= c-2) {
                if (map[xCor][yCor+1] == 1 || map[xCor][yCor+1] == 0) {
                    around.offer(new Points(xCor, yCor+1));
                }
            }
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