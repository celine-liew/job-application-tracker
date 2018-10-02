package model;

import Interfaces.Savable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JobList {

    protected List<Job> jobs = new ArrayList<>();
    protected static final String filename = "inputFile.csv";

    public JobList(List<List> jobLists) {
        for (List<String> l : jobLists) {
            restoreJob(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4));
        }
    }

    public JobList() {

    }

    public void saveJobs() throws IOException  {
        Savable save = new Save(filename);
        save.writeFile(jobs);
    }
    public void restoreJob(String jobTitle, String company, String dateApplied, String jobStatus, String dateLastChanged) {
        Job job;
        job = new Job(jobTitle, company, dateApplied, jobStatus, dateLastChanged);
        jobs.add(job);
    }

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


    // EFFECTS: return true if input index is not within Job list range
    public boolean invalidJoblistRange(int i) {
        if (i > jobs.size()- 1) {
            return true;
        }
        return false;
    }

}
