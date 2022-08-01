import java.util.*;
import java.io.*;

public class Map {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int number = io.nextInt();
        ArrayList<Roads> edgeList = new ArrayList<Roads>();
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                int roadL = io.nextInt();
                if (i < j) {
                    edgeList.add(new Roads(roadL, i+1, j+1));
                }
            }
        }
        Collections.sort(edgeList);
        UnionFind UF = new UnionFind(number+1);
        for (int k = 0; k < edgeList.size(); k++) {
            Roads thisOne = edgeList.get(k);
            int howLong = thisOne.length();
            int firstVillage = thisOne.village1();
            int secondVillage = thisOne.village2();
            if (!UF.isSameSet(firstVillage, secondVillage)) {
                io.println(firstVillage + " " + secondVillage);
                UF.unionSet(firstVillage, secondVillage);
            }
        }
        io.close();
    }  
}

// Roads Class
class Roads implements Comparable<Roads>{
    public Integer _length;
    public Integer _village1;
    public Integer _village2;

    public Roads(Integer firstN, Integer secondN, Integer thirdN) {
        _length = firstN;
        _village1 = secondN;
        _village2 = thirdN;
    }

    public int compareTo(Roads another) {
        if (!this.length().equals(another.length())) {
            return this.length() - another.length();
        } else if (!this.village1().equals(another.village1())) {
            return this.village1() - another.village1();
        } else {
            return this.village2() - another.village2();
        }
    }

    Integer length() { return _length; }
    Integer village1() { return _village1; }
    Integer village2() { return _village2; }
}

// Union-Find Disjoint Sets Library, using both path compression and union by rank heuristics
class UnionFind {
    public int[] p;
    public int[] rank;
    public int[] setSize;
    public int numSets;
  
    public UnionFind(int N) {
      p = new int[N];
      rank = new int[N];
      setSize = new int[N];
      numSets = N;
      for (int i = 0; i < N; i++) {
        p[i] = i;
        rank[i] = 0;
        setSize[i] = 1;
      }
    }
  
    public int findSet(int i) { 
      if (p[i] == i) return i;
      else {
        p[i] = findSet(p[i]);
        return p[i]; 
      } 
    }
  
    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  
    public void unionSet(int i, int j) { 
      if (!isSameSet(i, j)) { 
        numSets--; 
        int x = findSet(i), y = findSet(j);
        // rank is used to keep the tree short
        if (rank[x] > rank[y]) { 
            p[y] = x; 
            setSize[x] = setSize[x] + setSize[y]; 
        }
        else { 
            p[x] = y; 
            setSize[y] = setSize[x] + setSize[y];
          if (rank[x] == rank[y]) 
            rank[y] = rank[y]+1; 
        } 
      } 
    }
  
    public int numDisjointSets() { return numSets; }
  
    public int sizeOfSet(int i) { return setSize[findSet(i)]; }
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