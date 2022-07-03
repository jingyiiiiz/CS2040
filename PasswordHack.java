import java.util.*;

public class PasswordHack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.nextLine());
        double list [] = new double[total];
        double result = 0;

        for (int i = 1; i <= total; i++) {
            String [] raw = sc.nextLine().split(" ");
            double prob = Double.parseDouble(raw[1]);
            list[i-1] = prob;
        }

        Arrays.sort(list);

        for (int j = list.length - 1; j >= 0; j--) {
            double toAdd = list[j] * (list.length - j);
            result += toAdd;
        }
    System.out.println(result);
    }
}