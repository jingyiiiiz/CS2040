import java.util.*;

public class Best {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.nextLine());

        double bestTiming = 100000; // this is definitely greater than the greatest possible time which is 20*4 = 80
        Runner [] allMembers = new Runner[total]; // for sorting according to first leg time
        Runner [] restTiming = new Runner[total]; // for sorting according to second leg time

        for (int i = 0; i < total; i++) {
            String [] raw = sc.nextLine().split(" ");
            String theirName = raw[0];
            double first = Double.parseDouble(raw[1]);
            double rest = Double.parseDouble(raw[2]);
            allMembers[i] = (new Runner(theirName, first, rest));
            restTiming[i] = (new Runner(theirName, first, rest));
        }

        Arrays.sort(allMembers, new FirstTiming()); // sorting according to first leg time
        Arrays.sort(restTiming, new RestTiming()); // sorting according to second leg time

        Runner [] bestTeam = new Runner[4]; 
        Runner [] currentTeam = new Runner[4];

        for (int j = 0; j < total; j++) {
            double currentTiming = 0;
            currentTeam[0] = allMembers[j];
            currentTiming += allMembers[j].getAsFirst();

            int count = 1;
            int k = 0;

            while (count < 4) {
                if (allMembers[j].getName().equals(restTiming[k].getName()) == false) {
                    currentTeam[count] = restTiming[k];
                    count += 1;
                    currentTiming += restTiming[k].getAsRest();
                }
                k += 1;
            }

            if (currentTiming < bestTiming) {
                bestTiming = currentTiming;
                for (int l = 0; l < 4; l++) {
                    bestTeam[l] = currentTeam[l];
                }
            }
        }

        System.out.println(bestTiming);
        
        for (int m = 0; m < 4; m++) {
            System.out.println(bestTeam[m].getName());
        }
    }
}

// defining a class for runners
class Runner {
    public String name;
    public double asFirst;
    public double asRest;
    
    public Runner (String name, double asFirst, double asRest) {
        this.name = name;
        this.asFirst = asFirst;
        this.asRest = asRest;
    }

    public String getName() {
        return this.name;
    }

    public double getAsFirst() {
        return this.asFirst;
    }

    public double getAsRest() {
        return this.asRest;
    }
}

// comparator according to first leg time
class FirstTiming implements Comparator<Runner> {
    public int compare(Runner runner1, Runner runner2) {
        if (runner1.asFirst < runner2.asFirst) {
            return -1;
        }
        else if (runner1.asFirst > runner2.asFirst) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

// comparator according to second leg time
class RestTiming implements Comparator<Runner> {
    public int compare(Runner runner1, Runner runner2) {
        if (runner1.asRest < runner2.asRest) {
            return -1;
        }
        else if (runner1.asRest > runner2.asRest) {
            return 1;
        }
        else {
            return 0;
        }
    }
}