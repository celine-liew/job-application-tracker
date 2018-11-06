package model;

import Exceptions.InvalidEntryException;
import Interfaces.JoblistInterface;
import Interfaces.OrgListInterface;

import java.io.IOException;
import java.util.*;

public class CompanyList implements OrgListInterface {
    Map<String, ArrayList<Job>> jobsbyco;

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
        job.addListBelongs(this); //thus JobListInterface (Interface needs to be refactored)
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

    public Map<String, List<String>> returnJobsbyType(){
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

}

