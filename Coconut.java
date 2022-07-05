// Zhang Jingyi
// A0239855M

import java.util.*;

public class Coconut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSyllabus = sc.nextInt();
        int numPlayer = sc.nextInt();

        ArrayList<Player> list = new ArrayList<Player>();

        // add initial players to the array
        for (int i = 0; i < numPlayer; i++) {
            list.add(new Player(i + 1, 0));
        }
        
        // starts from the first player
        int thisPlayer = 0;

        while (list.size() > 1) {
            // starts from itself except for fist turning into downward palm
            thisPlayer = (thisPlayer + numSyllabus - 1) % list.size();
            int currState = list.get(thisPlayer).getState();

            if (currState == 0) {
                // folded hand turning into two fists
                list.set(thisPlayer, new Player(list.get(thisPlayer).getName(), 1));
                list.add(thisPlayer, new Player(list.get(thisPlayer).getName(), 1));
            } else if (currState == 1) {
                // fist turning into downward hand
                list.set(thisPlayer, new Player(list.get(thisPlayer).getName(), 2));
                // plus one to handle the case when fist turns into downward palm, next round starts from the next hand
                thisPlayer++;
            } else if (currState == 2) {
                // player removed
                list.remove(thisPlayer);
            }
        }
        // the only player left
        System.out.println(list.get(0).getName());

    }
}

// define a class for Players
class Player {
    public int name;
    public int state;

    public Player (int name, int state) {
        this.name = name;
        this.state = state;
    }

    public int getName() {
        return this.name;
    }

    public int getState() {
        return this.state;
    }
}