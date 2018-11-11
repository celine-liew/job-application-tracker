package model;

import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CoopJob extends Job {

    private String coopDuration;

    //EFFECTS: Create a new coop job, with coop-term length
    public CoopJob(int jobID, String jobTitle, String company) throws InvalidEntryException {
        super(jobID, jobTitle, company);
        this.jobType = CO_OP;
        coopDuration = "";
    }

    public CoopJob(String jobID, String jobType, String jobTitle, String company,
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
        System.out.println("Coop Job applied! Good Luck!");
    }

    public void InvalidChoice(int coopTerm) throws InvalidChoiceException, NumberFormatException {
        if (coopTerm == 0 || coopTerm >= 4) {
            throw new InvalidChoiceException();
        }
        return;
    }

    public void setCoopDuration (String coopTerm){
        int coopTermInt = Integer.parseInt(coopTerm);
        switch (coopTermInt) {
        case 1: coopDuration = "4 months";
        break;
        case 2: coopDuration = "8 months";
        break;
        case 3: coopDuration = "1 year";
        break;
    }

    super.setCoopDuration(coopDuration);
    }

}
