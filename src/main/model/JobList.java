package model;

import Exceptions.InvalidEntryException;
import Interfaces.JoblistInterface;
import Interfaces.Savable;
import Interfaces.Subject;

import java.io.IOException;
import java.util.*;

public class JobList extends Subject implements JoblistInterface  {

    protected HashMap<Integer, Job> jobs;
    private Job restorejob;
    int maxID;
    int jobID; //NEEDS TO BE IN THE WRITE FILE.
    private Job job;
    final String FULL_TIME = "Full-time";
    final String CO_OP = "Coop";
    protected EmailServer emailServer = new EmailServer();
    final String  REMOVED = "removed";
    final String ADDED= "added";
    final String UPDATED = "updated";

    //private CompanyList colist;// = new CompanyList();

    public JobList(List<List> jobLists) throws InvalidEntryException {
        jobs = new HashMap<>();
        // this.colist = companyList;
        for (List<String> l : jobLists) {
            restoreJob(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5), l.get(6), l.get(7));
        }
    }

    public JobList() {
        jobs = new HashMap<>();
        // this.colist = companyList;
    }

    public void restoreJob(String jobID, String jobType, String jobTitle, String company, String dateApplied, String jobStatus, String dateLastChanged, String coopDuration) throws InvalidEntryException {
        addObserver(emailServer);
        if (jobType.equalsIgnoreCase(CO_OP)){
            restorejob = new CoopJob(jobID, jobType, jobTitle, company,
                    dateApplied, jobStatus, dateLastChanged, coopDuration);
        } else if (jobType.equalsIgnoreCase(FULL_TIME)){
            restorejob = new FulltimeJob(jobID, jobType, jobTitle, company,
                    dateApplied, jobStatus, dateLastChanged, coopDuration);
        }
        int jobInt = Integer.parseInt(jobID);
        jobs.put(jobInt, restorejob);
        // colist.addJob(restorejob);
        if (jobInt > maxID)
            maxID = jobInt;
    }

    public void saveJobs(String filename) throws IOException, NullPointerException {
        Savable save = new Save(filename);
        List<Job> jobsSave = new ArrayList<>();
        for (Map.Entry<Integer, Job> entry : jobs.entrySet()) {
            jobsSave.add(entry.getValue());
        }
        save.writeFile(jobsSave);
    }

    // REQUIRES: i is within job list range
    // MODIFIES: nothing
    // EFFECTS: return job in given index of job list.
    public Job getJob(int i) {
        return jobs.get(i);
    }

    //EFFECTS: return true is job list is empty.
    public boolean jobLisEmpty() {
        return jobs.isEmpty();
    }

    //EFFECT: return job list
    public List<Job> getJobList() {
        List<Job> jobsPrint = new ArrayList<>();
        for (Map.Entry<Integer, Job> entry : jobs.entrySet()) {
            jobsPrint.add(entry.getValue());
        }
        return jobsPrint;
    }

    // EFFECTS: return true if input index is not within Job list range
    public boolean invalidJoblistRange(int i) {
        return !jobs.containsKey(i);
    }

    @Override
    public void removeJob(int i) {
//        emailServer.updateJob(REMOVED, jobs.get(i).jobType, jobs.get(i).jobTitle, jobs.get(i).company);
        notifyEmailObserver(REMOVED, jobs.get(i)); //TODO
        // jobs.get(i).removeListBelongs();
        jobs.remove(i);
    }

    // REQUIRES: jobTitle, company
    // MODIFIES: this
    // EFFECTS: creates a new job and add to job list.
    public void addJob(String jobType, String jobTitle, String company, String coopTerm) throws InvalidEntryException {
        if (jobType.equalsIgnoreCase(CO_OP)) {
            jobID = maxID + 1;
            maxID++;
            job = new CoopJob(jobID, jobTitle, company);
            job.setCoopDuration(coopTerm);
        } else {
            jobID = maxID + 1;
            maxID++;
            job = new FulltimeJob(jobID, jobTitle, company);
        }
        jobs.put(jobID, job);
        // colist.addJob(job);
        job.printApplied();
        notifyEmailObserver(ADDED, job);
    }

    public void newStatusJob(int i, String newStatus) {
        System.out.println(jobs.get(i).getJobStatus());
        String OLD_STATUS = (jobs.get(i).getJobStatus());
        jobs.get(i).setJobStatus(newStatus);
        jobs.get(i).setDateLastChanged();
        notifyEmailObserver(OLD_STATUS, newStatus, UPDATED, jobs.get(i));
    }

    }

