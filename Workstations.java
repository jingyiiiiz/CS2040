import java.util.*;

public class Workstations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Researcher [] researchers = new Researcher[n];
        // initialise priority queue for stations, station accessed through .poll() is always the one with the latest available time
        PriorityQueue<Integer> stations = new PriorityQueue<>();
        int result = 0;

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int s = sc.nextInt();
            researchers[i] = new Researcher(a, s);
        }

        // sort researchers based on their arriving time
        Arrays.sort(researchers, new ArrTime());

        for (int j = 0; j < n; j++) {
            Researcher toCheck = researchers[j];

            // access stations with the latest available time, pass through .poll() if it locks before next researcher arrives
            // stop the polling and passing process until the stations list is empty, or the station does not lock when the next researcher arrives
            while (stations.size() != 0 && stations.peek() + m < toCheck.getArr()) {
                stations.poll();
            }
            // if the stations list is empty, meaning that there is no suitable station, so just have a start a new one
            // if the current station with latest available time does not stop when next researcher arrives, also start a new station
            if (stations.size() == 0 || stations.peek() > toCheck.getArr()) {
                stations.add(toCheck.getLeave());
            }
            // if the current station can be used, increment result by 1 and update the station's available time accordingly
            else if (stations.peek() + m >= toCheck.getArr()) {
                result++;
                stations.poll();
                stations.add(toCheck.getLeave());
            }
        }

        System.out.println(result);
    }
}

class Researcher {
    public Integer arrTime;
    public Integer stayTime;

    public Researcher (Integer arrTime, Integer stayTime) {
        this.arrTime = arrTime;
        this.stayTime = stayTime;
    }

    public Integer getArr() {
        return this.arrTime;
    }

    public Integer getStay() {
        return this.stayTime;
    }

    public Integer getLeave() {
        return (this.arrTime + this.stayTime);
    }
}

class ArrTime implements Comparator<Researcher> {
    public int compare(Researcher researcher1, Researcher researcher2) {
        return researcher1.getArr() - researcher2.getArr();
    }
}