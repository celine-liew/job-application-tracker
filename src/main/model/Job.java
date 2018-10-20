package model;

import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;
import Interfaces.JobInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.String;

public abstract class Job implements JobInterface  {

    protected String jobType;
    protected String jobTitle; //private so that it is not visible outside of this Class. All operations done by getters/setters.
    protected String company;
    public LocalDate dateApplied; //Changed to date time function in Java. Set to public for testing.
    protected String jobStatus;
    protected LocalDate dateLastChanged;
    public final String APPLIED = "Applied";
    protected String coopDuration;

    // REQUIRES: jobTitle and company to be valid
    // MODIFIES: this
    // EFFECTS: creates new job object initialising private fields
    public Job(String jobTitle, String company) throws InvalidEntryException {

            this.jobType = "";
            this.jobTitle = jobTitle;
            this.company = company;
            this.dateApplied = LocalDate.now(); //used Java's function.
            this.jobStatus = APPLIED;  //hardcoding it currently.
            this.dateLastChanged = dateApplied;
            this.coopDuration = "n/a";
    }

//    public Job(String jobType, String jobTitle, String company, String date, String jobStatus, String dateLastChanged){
//        this.jobType = jobType;
//        this.jobTitle = jobTitle;
//        this.company = company;
//        this.dateApplied = LocalDate.now(); //used Java's function.
//        this.jobStatus = jobStatus;  //hardcoding it currently.
//        this.dateLastChanged = LocalDate.now();
//    }

    abstract void printApplied();

    public String getJobType() {
            return this.jobType;
        }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns the job title
    public String getJobTitle(){
        return jobTitle;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns company string
    public String getCompany(){
        return company;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns the date the job is applied
    public String getDateApplied(){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
//        String datechanged = dateApplied.format(formatter);
//        LocalDate parsedDate = LocalDate.parse(datechanged, formatter);
        String parsedDate = dateApplied.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
        return parsedDate; }

    // MODIFIES: this
    // EFFECTS: updates the date of change.
    public void setDateLastChanged() {
        this.dateLastChanged = LocalDate.now();
    }

    //EFFECTS: return the date of last changed
    public String getDateLastChanged() {
        //info from oracle documentation: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        String parsedDate = dateLastChanged.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
        return parsedDate;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns true
    public String getJobStatus(){
        return jobStatus;
    }

    // REQUIRES: job status that is not the current one.
    // MODIFIES: jobStatus
    // EFFECTS: sets the job status to a new status
    public void setJobStatus(String jobStatus){
        this.jobStatus = jobStatus;
    }

    public void setCoopDuration(String coopDuration) {this.coopDuration = coopDuration;}

    public String getCoopDuration () {return coopDuration;}


    public void InvalidChoice(int choice) throws InvalidChoiceException {}

}
