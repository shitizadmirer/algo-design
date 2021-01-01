package practice.DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Job {
    int profit, start, end;

    public Job(int profit, int start, int end) {
        this.profit = profit;
        this.start = start;
        this.end = end;
    }
}

public class WeightedJobScheduling {

    public List<Job> getNonOverlappingMaxProfitJobs(List<Job> jobs) {
        if (jobs.isEmpty()) {
            return new ArrayList<>();
        }
        int n = jobs.size();
        int[] profit = new int[n];
        int[] index = new int[n];
        Comparator<Job> jobComparator = Comparator.comparingInt(o -> o.start);
        jobs.sort(jobComparator);

        int maxProfit = 0;
        int maxIndex=-1;

        for (int i = 0; i < n; i++) {
            int curProfit = jobs.get(i).profit;
            index[i]=i;
            profit[i]=curProfit;
            int start = jobs.get(i).start;
            for (int j = 0; j < i; j++) {
                if (jobs.get(j).end <= start && profit[j]+curProfit>profit[i]) {
                    profit[i] = profit[j]+curProfit;
                    index[i]=j;
                }
            }
            if (profit[i]>maxProfit){
                maxProfit = profit[i];
                maxIndex = i;
            }
        }

        List<Job> resultJobs = new ArrayList<>();

        int k =maxIndex;
        do{
            resultJobs.add(jobs.get(k));
            k = index[k];
        }while (index[k]!=k);
        resultJobs.add(jobs.get(k));

        Collections.reverse(resultJobs);
        return resultJobs;
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(50,1,2));
        jobs.add(new Job(20,3,5));
        jobs.add(new Job(100,6,19));
        jobs.add(new Job(200,2,100));
        jobs.add(new Job(90, 18,22));
        jobs.add(new Job(100, 101,122));

        List<Job> resultJobs = new WeightedJobScheduling().getNonOverlappingMaxProfitJobs(jobs);

        for (Job job : resultJobs){
            System.out.print(job.profit);
            System.out.print(" ");
            System.out.print(job.start);
            System.out.print(" ");
            System.out.print(job.end);
            System.out.println();
        }
    }
}
