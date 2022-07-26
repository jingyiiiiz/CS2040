// Zhang Jingyi
// A0239855M

import java.util.*;
import java.io.*;
import java.time.chrono.ThaiBuddhistChronology;

public class Dominos {
    public static ArrayList [] adjList;
    public static HashSet<Integer> visited;
    public static HashSet<Integer> down;
    public static HashSet<Integer> startingPt;
    public static Queue<Integer> toVisit;
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int number = io.nextInt();
        for (int i = 0; i < number; i ++) {
            int n = io.nextInt();
            int m = io.nextInt();
            adjList = new ArrayList[n];
            down = new HashSet<Integer>();
            // set for starting points
            startingPt = new HashSet<Integer>();
            toVisit = new ArrayDeque<Integer>();
            for (int k = 0; k < n; k ++) {
                adjList[k] = new ArrayList<Integer>();
            }
            for (int j = 0; j < m; j++) {
                int x = io.nextInt()-1;
                int y = io.nextInt()-1;
                adjList[x].add(y);
            }
            for (int l = 0; l < n; l++) {
                if (down.contains(l) == false) {
                    // record all points visited from a particular point
                    visited = new HashSet<Integer>();
                    startingPt.add(l);
                    visited.add(l);
                    down.add(l);
                    for (int c = 0; c < adjList[l].size(); c++) {
                        int around = (int) adjList[l].get(c);
                        toVisit.offer(around);
                        down.add(around);
                        visited.add(around);
                    }
                    while (toVisit.isEmpty() == false) {
                        int thatPt = toVisit.poll();
                        if (startingPt.contains(thatPt) == true) {
                            startingPt.remove(thatPt);
                        }
                        for (int a = 0; a < adjList[thatPt].size(); a++) {
                            int itsNeighbour = (int) adjList[thatPt].get(a);
                            if (thatPt != itsNeighbour) {
                                if (visited.contains(itsNeighbour) == false) {
                                    toVisit.offer(itsNeighbour);
                                    down.add(itsNeighbour);
                                    visited.add(itsNeighbour);
                                    adjList[thatPt].set(a, thatPt);
                                }
                            }
                        }
                    }
                    // avoid visiting multiple times
                    adjList[l] = new ArrayList<Integer>();

                    /*for (int a = 0; a < adjList[l].size(); a++) {
                        int thatPt = (int) adjList[l].get(a);
                        if (thatPt != l) {
                            if (startingPt.contains(thatPt)) {
                                startingPt.remove(thatPt);
                            }
                            else if (visited[thatPt] == true) {
                                continue;
                            } 
                            else {
                                toVisit.add(thatPt);
                            }
                        }
                    }
                    while (!toVisit.isEmpty()) {
                        int ptToVisit = toVisit.poll();
                        visited[ptToVisit] = true;
                        if (startingPt.contains(ptToVisit)) {
                            startingPt.remove(ptToVisit);
                        }
                        for (int b = 0; b < adjList[ptToVisit].size(); b++) {
                            int ptInSub = (int) adjList[ptToVisit].get(b);
                            if (visited[ptInSub] == true) {
                                continue;
                            }
                            else {
                                toVisit.add(ptInSub);
                            }
                        }
                    } */
                }
                
            }

            
        io.println(startingPt.size());
    }
    io.close();
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