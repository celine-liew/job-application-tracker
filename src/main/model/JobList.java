package model;

import java.util.ArrayList;
import java.util.List;

public class JobList {

    private List<Job> jobs = new ArrayList<>();

    // REQUIRES: jobTitle, company
    // MODIFIES: this
    // EFFECTS: creates a new job and add to job list.
    public void addJob(String jobTitle, String company) {
        Job job;
        job = new Job(jobTitle, company);
        jobs.add(job);
    }

    // REQUIRES: i is within job list range
    // MODIFIES: nothing
    // EFFECTS: return job in given index of job list.
    public Job getJob(int i){
        return jobs.get(i);
    }

    //EFFECTS: return true is job list is empty.
    public boolean jobLisEmpty() {
        return jobs.isEmpty();
    }

    //EFFECT: return job list
    public List<Job> getJobList() {
        return jobs;
    }


    // EFFECTS: return true if input index is within Job list range
    public boolean validJoblistRange(int i) {
        if (i <= jobs.size()- 1) {
            return true;
        }else {
            return false;
        }
    }

}
