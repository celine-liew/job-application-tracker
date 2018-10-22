package model;

import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;

public class FulltimeJob extends Job {

    public final String FULL_TIME = "Full-time";

    //EFFECTS: Create a new full time job
    public FulltimeJob(String jobTitle, String company) throws InvalidEntryException {
        super(jobTitle, company);
        this.jobType = FULL_TIME;
    }

    public void printApplied(){
        System.out.println("Full-time Job applied! Get the Job!");
    }

}
