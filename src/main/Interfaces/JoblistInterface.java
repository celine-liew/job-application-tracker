package Interfaces;

import Exceptions.InvalidEntryException;
import model.Job;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface JoblistInterface {

    public void saveJobs(String filename) throws IOException;

    public void addJob(String jobType, String jobTitle, String company) throws InvalidEntryException;

    public Job getJob(int i);

    public boolean jobLisEmpty();

    public Collection<Job> getJobList();

    public boolean invalidJoblistRange(int i);

    public void removeJob(int i);

    public void removeJob(Job job);
}
