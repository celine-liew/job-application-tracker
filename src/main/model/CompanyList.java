package model;

import Exceptions.InvalidEntryException;
import Interfaces.JoblistInterface;

import java.io.IOException;
import java.util.*;

public class CompanyList implements JoblistInterface {
    Map<String, ArrayList<Job>> jobsbyco;
    String company;
    JobList jobList;

    public CompanyList() {
        jobsbyco = new HashMap<>();
    }

    public void addJob(Job job) {
        if (!jobsbyco.containsKey(job.getCompany())) {
            jobsbyco.put(job.getCompany(), new ArrayList<>());
            jobsbyco.get(job.getCompany()).add(job);
        } else {
            if (!jobsbyco.get(job.getCompany()).contains(job)) {
                jobsbyco.get(job.getCompany()).add(job);
            }
        }
        job.addListBelongs(this);
    }

    public void removeJob(Job job) {
        if (jobsbyco.containsKey(job.getCompany())) {
            ArrayList array = jobsbyco.get(job.getCompany());
            array.remove(job);
            if (array.isEmpty()){
                jobsbyco.remove(job.getCompany());
            }
        }
    }

    public Map<String, List<String>> returnJobsbyCo(){
        //List<Job> jobsPrint = new ArrayList<>();
        Map<String, List<String>> jobsPrint = new HashMap<>();

        for (Map.Entry<String, ArrayList<Job>> entry : jobsbyco.entrySet()) {
            List<String> titleList = new ArrayList<>();
            for (Job j : entry.getValue()){
                titleList.add(j.getJobTitle());
            }
            jobsPrint.put(entry.getKey(), titleList);
        }
        return jobsPrint;
    }

    @Override
    public void saveJobs(String filename) throws IOException {

    }

    @Override
    public void addJob(String jobType, String jobTitle, String company) throws InvalidEntryException {

    }

    @Override
    public Job getJob(int i) {
        return null;
    }

    @Override
    public boolean jobLisEmpty() {
        return false;
    }

    @Override
    public Collection<Job> getJobList() {
        return null;
    }

    @Override
    public boolean invalidJoblistRange(int i) {
        return false;
    }

    @Override
    public void removeJob(int i) {

    }
}

