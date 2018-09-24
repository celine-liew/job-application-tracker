package model;

import java.util.ArrayList;
import java.util.List;

public class JobList {

    private List<Job> jobs = new ArrayList<>();

    public void addJob(String jobTitle, String company) {
        Job job;
        job = new Job(jobTitle, company);
        jobs.add(job);
    }

    public List<Job> getJobList() {
        return jobs;
    }

}
