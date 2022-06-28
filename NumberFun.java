import java.util.Scanner;

public class NumberFun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();

        for (int i = 1; i <= total; i++) {
            float a = sc.nextFloat();
            float b = sc.nextFloat();
            float c = sc.nextFloat();

            if (c == a + b) {
                System.out.println("Possible");
            } else if ((c == a - b) || (c == b - a)) {
                System.out.println("Possible");
            } else if (c == a * b) {
                System.out.println("Possible");
            } else if ((c == a / b) || (c == b / a)) {
                System.out.println("Possible");
            } else {
                System.out.println("Impossible");
            }
        }
    }
}
