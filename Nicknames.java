// Zhang Jingyi
// A0239855M

// When doing this assginment, I have verbally disccused the method to use with Qiu Ruitong and Lyu Lanqing, without directly looking at or referring to their codes.

import java.util.*;
import java.io.*;

public class Nicknames {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int a = io.nextInt();
        // to keep track of names and nicknames
        HashMap<Character, AVLTree> reference = new HashMap<Character, AVLTree>();
        HashMap<String, Long> occurrence = new HashMap<String, Long>();

        for (int i = 0; i < a; i++) {
            String name = io.next();
            char start = name.charAt(0);
            if (reference.containsKey(start) == false) {
                AVLTree newTree = new AVLTree();
                reference.put(start, newTree);
            }
            reference.get(start).insert(name);
        }

        int b = io.nextInt();

        for (int j = 0; j < b; j++) {
            String initial = io.next();
            // very first letter
            char starting = initial.charAt(0);

            // if not in reference, meaning it never occurred
            if (reference.containsKey(starting) == false) {
                io.println(0);
            } 
            // if nicknames haven't been called -> put findNum results in and return
            else if (occurrence.containsKey(initial) == false) {
                occurrence.put(initial, reference.get(starting).findNum(initial));
                io.println(occurrence.get(initial));
            } 
            // if called before -> directly get from the hashmap
            else {
                io.println(occurrence.get(initial));
            }
        }

        io.close();
    }
}

// AVL Vertex class
class AVLVertex {
    AVLVertex (String v) {
        key = v;
        parent = left = right = null;
        height = 0;
    }

    public AVLVertex parent, left, right;
    public String key;
    public int height;
    public int size;
}

// AVL Tree class
class AVLTree {
    public AVLVertex root;

    AVLTree() { root = null; }

    // update height for involved vertices when there are changes
    public void updateHeight(AVLVertex T) {
        if (T.right == null && T.left == null) {
            T.height = 0;
        } else if (T.left == null) {
            T.height = T.right.height + 1;
        } else if (T.right == null) {
            T.height = T.left.height + 1;
        } else {
            T.height = Math.max(T.left.height, T.right.height) + 1;
        }
    }

    // get balancing factor
    public int getBF(AVLVertex T) {
        if (T == null) {
            return 0;
        }
        else if (T.right == null && T.left == null) {
            return 0;
        } else if (T.left == null) {
            return - (T.right.height + 1);
        } else if (T.right == null) {
            return T.left.height + 1;
        } else {
            return T.left.height - T.right.height;
        }
    }

    // left rotate
    public AVLVertex leftRotate(AVLVertex T) {
        AVLVertex w = T.right;
        w.parent = T.parent;
        T.parent = w;
        T.right = w.left;
        if (w.left != null) {
            w.left.parent = T;
        }
        w.left = T;
        // update height for both T and w
        updateHeight(T);
        updateHeight(w);
        return w;
    }

    // right rotate, generally similar to the above, just mirror actions
    public AVLVertex rightRotate(AVLVertex T) {
        AVLVertex w = T.left;
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;
        if (w.right != null) {
            w.right.parent = T;
        }
        w.right = T;
        updateHeight(T);
        updateHeight(w);
        return w;
    }

    // insert into the tree -- start from the root
    public void insert(String v) {
        root = insert(root, v);
    }

    // insert to a particular vertex, recursive process
    public AVLVertex insert (AVLVertex T, String key) {
        if (T == null) {
            return new AVLVertex(key);
        } else if (key.compareTo(T.key) < 0) {
            T.left = insert(T.left, key);
            T.left.parent = T;
        } else {
            T.right = insert(T.right, key);
            T.right.parent = T;
        }
        // update height of T
        updateHeight(T);
        return balance(T);
    }

    // balance the tree
    public AVLVertex balance (AVLVertex T) {
        int bf = getBF(T);
        int bfLeft = getBF(T.left);
        int bfRight = getBF(T.right);

        // take care of each of the four scenarios
        if (bf == 2 && bfLeft >= 0 ) {
            return rightRotate(T);
        } else if (bf == 2 && bfLeft == -1) {
            T.left = leftRotate(T.left);
            return rightRotate(T);
        } else if (bf == -2 && bfRight <= 0) {
            return leftRotate(T);
        } else if (bf == -2 && bfRight == -1) {
            T.right = rightRotate(T.right);
            return leftRotate(T);
        }

        return T;
    }

    // find the number of names that starts with a particular prefix inside the tree
    // into the root
    public long findNum(String v) {
        long num = findNum(root, v);
        return num;
    }

    // at a particular vertex T
    public long findNum(AVLVertex T, String v) {
        // no such names
        // base case
        if (T == null) {
            return 0; 
        } 
        // smaller -> find in right children
        else if (T.key.compareTo(v) < 0) {
            return findNum(T.right, v);
        } 
        // equal -> increment the number by 1 and search in both left and right children
        else if (T.key.indexOf(v) == 0) {
            return 1 + findNum(T.right, v) + findNum(T.left, v);
        } 
        // larger -> find in left children
        else {
            return findNum(T.left, v);
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