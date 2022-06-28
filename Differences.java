import java.util.Scanner;

public class Differences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();

        for (int i = 1; i <= total; i++) {
            String first = sc.next();
            String second = sc.next();
            String result = "";

            System.out.println(first);
            System.out.println(second);

            String [] a = first.split("");
            String [] b = second.split("");

            for (int j = 0; j < first.length(); j++) {
                if (a[j].equals(b[j])) {
                    result += ".";
                } else {
                    result += '*';
                }
            }

            System.out.println(result);
            System.out.println();

        }
    }
}
