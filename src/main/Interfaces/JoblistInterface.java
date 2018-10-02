package Interfaces;

import model.Job;

import java.io.IOException;
import java.util.List;

public interface JoblistInterface {

    public void saveJobs() throws IOException;

    public void addJob(String jobTitle, String company);

    public Job getJob(int i);

    public boolean jobLisEmpty();

    public List<Job> getJobList();

    public boolean invalidJoblistRange(int i);
}
