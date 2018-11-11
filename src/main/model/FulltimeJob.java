package model;

import Exceptions.InvalidEntryException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FulltimeJob extends Job {



    //EFFECTS: Create a new full time job
    public FulltimeJob(int jobID, String jobTitle, String company) throws InvalidEntryException {
        super(jobID, jobTitle, company);
        this.jobType = FULL_TIME;
    }

    public FulltimeJob(String jobID, String jobType, String jobTitle, String company,
                   String dateApplied, String jobStatus, String dateLastChanged, String coopDuration) throws InvalidEntryException {
        super(Integer.parseInt(jobID), jobTitle, company);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
        this.dateApplied = LocalDate.parse(dateApplied, formatter); //used Java's function.
        this.dateLastChanged = LocalDate.parse(dateLastChanged, formatter);
        this.jobStatus = jobStatus;
        this.jobType = jobType;
        this.coopDuration = coopDuration;
    }

    public void printApplied(){
        System.out.println("Full-time Job applied! Get the Job!");
    }

}
