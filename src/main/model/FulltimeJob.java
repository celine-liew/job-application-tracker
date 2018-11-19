package model;

import Exceptions.InvalidEntryException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FulltimeJob extends Job {



    //EFFECTS: Create a new full time job
    public FulltimeJob(int jobID, String jobTitle, String company) throws InvalidEntryException {
        super(jobID, jobTitle, company);
        this.jobTypeProperty().set(CO_OP);
    }

    public FulltimeJob(String jobID, String jobType, String jobTitle, String company,
                   String dateApplied, String jobStatus, String dateLastChanged, String coopDuration) throws InvalidEntryException {
        super(Integer.parseInt(jobID), jobTitle, company);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
        // this.dateApplied = LocalDate.parse(dateApplied, formatter); //used Java's function.
        this.dateAppliedProperty().set(dateApplied);
        // this.dateLastChanged = LocalDate.parse(dateLastChanged, formatter);
        this.dateLastChangedProperty().set(dateLastChanged);
        // this.jobStatus = jobStatus;
        this.jobStatusProperty().set(jobStatus);
        // this.jobType = jobType;
        this.jobTypeProperty().set(jobType);
        // this.coopDuration = coopDuration;
        this.coopDurationProperty().set(coopDuration);
    }

    public void printApplied(){
        System.out.println("Full-time Job applied! Get the Job!");
    }

}
