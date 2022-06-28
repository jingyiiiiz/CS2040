import java.util.Scanner;

public class Factorial {

    public static int get_fact(int a) {
        int result = 1;

        for (int j = 1; j <= a; j++) {
            result *= j;
        }
        return (result % 10);
      }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total;

        total = sc.nextInt();

        for (int i = 1; i <= total; i++) {
            int raw = sc.nextInt();
            System.out.println(get_fact(raw));
        }
    }
}
