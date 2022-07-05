import java.util.*;

public class FroshWeek {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTasks = sc.nextInt();
        int numIntervals = sc.nextInt();
        int [] tasks = new int[numTasks];
        int [] intervals = new int[numIntervals];

        for (int i=0; i<numTasks; i++) {
            int toAdd = sc.nextInt();
            tasks[i] = toAdd;
        }
        Arrays.sort(tasks);

        for (int j=0; j<numIntervals; j++) {
            int toAdded = sc.nextInt();
            intervals[j] = toAdded;
        }
        Arrays.sort(intervals);

        int k = 0;
        int l = 0;
        int result = 0;
        while (k<numTasks && l<numIntervals) {
            if (tasks[k] <= intervals[l]) {
                k++;
                l++;
                result++;
            } else {
                l++;
            }
        }

        System.out.println(result);
    }
}