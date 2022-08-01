import java.util.*;

public class Card {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // extract n, t, k from the first line
        int n = sc.nextInt();
        int t = sc.nextInt();
        int k = sc.nextInt();

        int [] numCards = new int[t];
        Cards [] sequence = new Cards[t];
        // extract the list of cards from the second line
        for (int m = 0; m < n; m++) {
            int toAdd = sc.nextInt();
            numCards[toAdd - 1]++;
        }

        // extracting each buy and sell price for each card type
        for (int i = 0; i < t; i++) {
            long buyP = sc.nextInt();
            long sellP = sc.nextInt();
            
            // add them to the new card
            sequence[i] = (new Cards(i+1, buyP, sellP, numCards[i]));
        }

        // sort according to the Overall defined, in descending order, that is, those with higher values gained from buying and not selling will be put in front
        Arrays.sort(sequence, new Overall());

        int l = 0;
        long profit = 0;
        while (l < k) {
            profit -= (2 - numCards[sequence[l].name - 1]) * sequence[l].buyPrice;
            l += 1;
        }
        while (l < t) {
            profit += numCards[sequence[l].name - 1] * sequence[l].sellPrice;
            l += 1;
        }

        System.out.println(profit);
    }
}

class Cards {
    public int name;
    public int owned;
    public long buyPrice;
    public long sellPrice;

    public Cards (int name, long buyPrice, long sellPrice, int owned) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice; 
        this.owned = owned;
    }

    public long shouldOwn() {
        if (this.owned == 0) {
            return - 2 * buyPrice;
        } else if (this.owned == 1) {
            return - buyPrice - sellPrice;
        } else {
            return - 2 * sellPrice;
        }
    }
}

class Overall implements Comparator<Cards> {
    public int compare(Cards card1, Cards card2) {
        if (card2.shouldOwn() < card1.shouldOwn()) {
            return -1;
        }
        else if (card2.shouldOwn() > card1.shouldOwn()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
