// A0239855M
// Zhang Jingyi

import java.util.*;

public class SortOfSorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int count = 1;

        while (n != 0) {
            if (count > 1) {
                System.out.println();
            }

            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String name = sc.nextLine();
                list.add(name);
            }
            List<String> backup = new ArrayList<>(list);
            List<String> result = new ArrayList<>();
            Collections.sort(list);

            int j = 0;
            while (j <= list.size() - 1) {
                String firstTwo = list.get(j).substring(0, 2);
                List<Integer> same = new ArrayList<>();

                for (int k = 0; k <= backup.size() - 1; k++) {
                    String toCompare = backup.get(k).substring(0, 2);
                    if (toCompare.equals(firstTwo) == true) {
                        same.add(k);
                    }
                }

                for (int l = 0; l <= same.size() - 1; l++) {
                    result.add(backup.get(same.get(l)));
                }

                j += same.size();

            }

            for (int m = 0; m <= result.size() - 1; m++) {
                System.out.println(result.get(m));
            }
            
        n = Integer.parseInt(sc.nextLine());
        count += 1;
        }

        return;
    }
}
