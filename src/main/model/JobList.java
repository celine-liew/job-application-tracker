package model;

import Exceptions.InvalidEntryException;
import Interfaces.JoblistInterface;
import Interfaces.Savable;

import java.io.IOException;
import java.util.*;

public class JobList implements JoblistInterface {

    protected HashMap<Integer, Job> jobs = new HashMap<>();
    private Job restorejob;
    int maxID;
    int jobID; //NEEDS TO BE IN THE WRITE FILE.
    private Job job;

    private CompanyList colist;// = new CompanyList();

    public JobList(CompanyList companyList, List<List> jobLists) throws InvalidEntryException {
        //jobs = new HashMap<>();
        this.colist = companyList;
        for (List<String> l : jobLists) {
            restoreJob(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5), l.get(6), l.get(7));
        }
        restorejob.printApplied();
    }

    public JobList(CompanyList companyList) {
        jobs = new HashMap<>();
        this.colist = companyList;
    }

    public void saveJobs(String filename) throws IOException, NullPointerException {
        Savable save = new Save(filename);
        List<Job> jobsSave = new ArrayList<>();
        for (Map.Entry<Integer, Job> entry : jobs.entrySet()) {
            jobsSave.add(entry.getValue());
        }
        save.writeFile(jobsSave);
    }

    public void restoreJob(String jobID, String jobType, String jobTitle, String company, String dateApplied, String jobStatus, String dateLastChanged, String coopDuration) throws InvalidEntryException {
        restorejob = new allJob(jobID, jobType, jobTitle, company, dateApplied, jobStatus, dateLastChanged, coopDuration);
        int jobInt = Integer.parseInt(jobID);
        jobs.put(jobInt, restorejob);
        colist.addJob(restorejob);
        if (jobInt > maxID)
            maxID = jobInt;
    }

    // REQUIRES: jobTitle, company
    // MODIFIES: this
    // EFFECTS: creates a new job and add to job list.
    public void addJob(String jobType, String jobTitle, String company, String coopTerm) throws InvalidEntryException {
        if (jobType.equalsIgnoreCase("1")) {
            jobID = maxID + 1;
            maxID++;
            job = new CoopJob(jobID, jobTitle, company); //TODO
            job.setCoopDuration(coopTerm);
        } else {
            jobID = maxID + 1;
            maxID++;
            job = new FulltimeJob(jobID, jobTitle, company);
        }
        jobs.put(jobID, job);
        colist.addJob(job);
        job.printApplied();
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
        jobs.get(i).removeListBelongs();
        jobs.remove(i);
    }

//    public void addJob(String jobType, String jobTitle, String company) throws InvalidEntryException {
//        String coopTerm;
//        if (jobType.equalsIgnoreCase("1")) {
//            jobID = maxID + 1;
//            maxID++;
//            job = new CoopJob(jobID, jobTitle, company); //TODO
//
//            do {
//                retryEntry = false;
//                System.out.println("Select coop duration:\n" +
//                        "1) 4 months\n" +
//                        "2) 8 months\n" +
//                        "3) 1 year.");
//                try {
//                    coopTerm = scanner.nextLine();
//                    job.setCoopDuration(coopTerm);
//                } catch (NumberFormatException e) {
//                    System.out.println("Not a number.");
//                    retryEntry = true;
//                }
//
//            } while (retryEntry);
//
//
//        } else {
//            jobID = maxID + 1;
//            maxID++;
//            job = new FulltimeJob(jobID, jobTitle, company);
//
//        }
//        jobs.put(jobID, job);
//        colist.addJob(job);
//        job.printApplied();
//    }

    }


