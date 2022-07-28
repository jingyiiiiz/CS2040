// Zhang Jingyi
// A0239855M

import java.util.*;
import java.io.*;

public class Cannonball {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        double currX = io.nextDouble();
        double currY = io.nextDouble();
        Coordinates current = new Coordinates(currX, currY);
        double destX = io.nextDouble();
        double destY = io.nextDouble();
        Coordinates destination = new Coordinates(destX, destY);
        int number = io.nextInt();

        // keep track of all points
        Coordinates [] allPoints = new Coordinates[number+2];
        // keep track of all shortest timings
        double [] D = new double[number+2];
        // adjacency matrix
        double [][] adjMatrix = new double [number+2][number+2];

        // put everything in place
        allPoints[0] = current;
        D[0] = 0;
        for (int i = 0; i < number; i++) {
            double thisX = io.nextDouble();
            double thisY = io.nextDouble();
            allPoints[i+1] = new Coordinates(thisX, thisY);
            D[i+1] = Double.MAX_VALUE;
        }
        allPoints[number+1] = destination;
        D[number+1] = Double.MAX_VALUE;

        // including adjacency matrix
        for (int j = 0; j < number+2; j++) {
            for (int k = 0; k < number+2; k++) {
                if (j == 0) {
                    adjMatrix[j][k] = allPoints[j].timeRun(allPoints[k]);
                } else {
                    adjMatrix[j][k] = allPoints[j].timeFly(allPoints[k]);
                }
            }
        }

        // set up priority queue
        // similar to Modified Dijkstra's
        PriorityQueue<IntegerPair1> toVisit = new PriorityQueue<IntegerPair1>();
        
        // put every point into priority queue
        for (int l = 0; l < number+2; l++) {
            toVisit.add(new IntegerPair1(D[l], l));
        }
        // Modified Dijkstra's
        while (toVisit.isEmpty() == false) {
            IntegerPair1 toLook = toVisit.poll();
            double d = toLook.first();
            int u = toLook.second();
            if (d == D[u]) {
                for (int m = 0; m < number+2; m++) {
                    if (D[m] > D[u] + adjMatrix[u][m]) {
                        D[m] = D[u] + adjMatrix[u][m];
                        toVisit.add(new IntegerPair1(D[m], m));
                    }
                }
            }
        }
        // output
        io.print(D[number+1]);

        io.close();
    }
}

// Coordinates class
class Coordinates {
    public double _xCor;
    public double _yCor;

    public Coordinates(double first, double second) {
        _xCor = first;
        _yCor = second;
    }

    double xCor() { return _xCor; }
    double yCor() { return _yCor; }

    public double distance(Coordinates another) {
        return Math.sqrt(Math.pow(this.xCor() - another.xCor(), 2) + Math.pow(this.yCor() - another.yCor(), 2));
    }

    public double timeRun(Coordinates another) {
        return this.distance(another) / 5;
    }

    public double timeFly(Coordinates another) {
        if (this.distance(another) == 0) {
            return 0;
        } else if (this.distance(another) < 50) {
            return Math.min(((50 - this.distance(another)) / 5 + 2), this.distance(another) / 5);
        } else {
            return 2 + ((this.distance(another) - 50) / 5);
        }
    }
}

// IntegerPair1 class
// only difference is that first becomes double representing timings
class IntegerPair1 implements Comparable<IntegerPair1> {
    double _first;
    Integer _second;
  
    public IntegerPair1(double f, Integer s) {
      _first = f;
      _second = s;
    }
  
    public int compareTo(IntegerPair1 o) {
      if (this.first() != o.first()) {
        if (this.first() - o.first() < 0){
            return -1;
        } else {
            return 1;
        }
      }
      else
        return this.second() - o.second();
    }
  
    double first() { return _first; }
    Integer second() { return _second; }
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