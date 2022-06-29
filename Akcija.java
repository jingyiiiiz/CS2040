import java.util.*;

public class Akcija {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.nextLine());
        int result = 0;
        int list [] = new int[total];

        for (int i = 1; i <= total; i++) {
            int that = Integer.parseInt(sc.nextLine());
            list[i-1] = that;
        }
        Arrays.sort(list);

        for (int j = total-1; j >= total % 3 + 2; j-=3) {
            result += list[j];
            result += list[j-1];
        }

        for (int g = 0; g <= total % 3 - 1; g++) {
            result += list[g];
        }
        System.out.println(result);
    }
}
