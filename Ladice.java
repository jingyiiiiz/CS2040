import java.util.*;
import java.io.*;

public class Ladice {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int n = io.nextInt();
        int l = io.nextInt();
        UnionFind drawers = new UnionFind(l);

        for (int i = 0; i < n; i++) {
            int a = io.nextInt() - 1;
            int b = io.nextInt() - 1;
            drawers.unionSet(a, b);
            if (drawers.vacancy(a) >= 0) {
                io.println("LADICA");
            } else {
                io.println("SMECE");
            }
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

// union find class
class UnionFind {                                              
    public int[] p;
    public int[] rank;
    public int[] vacancy;
  
    public UnionFind(int N) {
      p = new int[N];
      rank = new int[N];
      vacancy = new int[N];
      
      for (int i = 0; i < N; i++) {
        p[i] = i;
        rank[i] = 0;
        // each drawer has an initial vacancy of 1 for incoming items
        vacancy[i] = 1;
      }
    }
  
    public int findSet(int i) { 
      if (p[i] == i) return i;
      else {
        p[i] = findSet(p[i]);
        return p[i]; 
      } 
    }

    public int vacancy(int i) {
        int setNum = findSet(i);
        // updating vacancy of the drawer from the overarching set drawer number
        return vacancy[setNum];
    }
  
    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  
    public void unionSet(int i, int j) { 
        int x = findSet(i), y = findSet(j);
        if (!isSameSet(i, j)) { 
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) {
                p[y] = x;
                if (vacancy[x] < 0 || vacancy[y] < 0) {
                    // if one of the drawers has negative vacancy, there is no way anything can fit in
                    // no need to deduct vacancy since SMECE is to be printed anyway
                    vacancy[x] = vacancy[y] + vacancy[x];
                } else {
                    // deduct vacancy by 1, meaning to put one item inside
                    vacancy[x] = vacancy[y] + vacancy[x] - 1;
                }
            } else { 
                p[x] = y;
                if (vacancy[x] < 0 || vacancy[y] < 0) {
                    // same as above
                    vacancy[y] = vacancy[x] + vacancy[y];
                } else {
                    // same as above
                    vacancy[y] = vacancy[y] + vacancy[x] - 1;
                }
            if (rank[x] == rank[y]) {
                rank[y] = rank[y]+1; 
                } 
            }
        } else {
            if (vacancy[x] >= 0) {
                // if same set (already combined), no need to increment by another's amount, just reduce by 1
                vacancy[x] -= 1;
            } else {
                // if already negative, meaning nothing can be put inside
                // just keep as -1
                vacancy[x] = -1;
            }
        }
    }
  }