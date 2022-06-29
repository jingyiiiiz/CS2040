import java.util.*;

public class Electrical {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] first = sc.nextLine().split(" ");
        int x1 = Integer.parseInt(first[0]);
        int y1 = Integer.parseInt(first[1]);
        String [] second = sc.nextLine().split(" ");
        int x2 = Integer.parseInt(second[0]);
        int y2 = Integer.parseInt(second[1]);
        int toCompare = Integer.parseInt(sc.nextLine());
        int xDiff = Math.abs(x1-x2);
        int yDiff = Math.abs(y1-y2);
        int totalDiff = xDiff + yDiff;
        Character result;

        if (totalDiff > toCompare) {
            result = 'N';
        }
        else if ((toCompare - totalDiff) % 2 != 0 ) {
            result = 'N';
        }
        else {
            result = 'Y';
        }
        System.out.println(result);
    }
}
