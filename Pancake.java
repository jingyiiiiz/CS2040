import java.util.*;

public class Pancake {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= total; i++) {
            int numOrder = Integer.parseInt(sc.nextLine());
            String name = sc.nextLine();

            Boolean ps = false;
            Boolean pc = false;

            for (int j = 1; j <= numOrder; j++) {
                String order = sc.nextLine();

                if (order.equals("pancakes")) {
                    pc = true;
                }
                else if (order.equals("pea soup")) {
                    ps = true;
                }
            }

            if (pc == true && ps == true) {
                System.out.println(name);
                return;
            }

        }
        System.out.println("Anywhere is fine I guess");

    }
}
