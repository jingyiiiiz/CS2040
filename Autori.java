import java.util.Scanner;

public class Autori {
    public static void main(String[] args) {
        String input;
        int i;

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        String [] splitted = input.split("-");

        for (i = 0; i < splitted.length; i++) {
            System.out.print(splitted[i].charAt(0));
        }
    }
}
