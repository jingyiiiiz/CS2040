import java.util.*;

public class Bootstrapping {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float number = Float.parseFloat(sc.nextLine());

        double left = 0;
        double right = 10;
        double middle = (left + right) / 2;

        while (left < right) {
            if (Math.pow(middle, middle) > number) {
                right = middle - 0.000001;
            }
            else if (Math.pow(middle, middle) < number) {
                left = middle + 0.000001;
            }
            middle = (left + right) / 2;
        }

        System.out.println(middle);
    }
}
