import java.util.Scanner;

public class Pot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        int result = 0;

        for (int i = 1; i <= total; i++) {
            int raw = sc.nextInt();
            int power = raw % 10;
            int num = (raw - power) / 10;

            result += Math.pow(num, power);
        }

        System.out.println(result);
    }
}