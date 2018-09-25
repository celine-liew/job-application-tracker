package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Job {

    private String jobTitle; //private so that it is not visible outside of this Class. All operations done by getters/setters.
    private String company;
    public LocalDate dateApplied; //Changed to date time function in Java. Set to public for testing.
    private String jobStatus;
    private LocalDate dateLastChanged;

    // REQUIRES: jobTitle and company
    // MODIFIES: this
    // EFFECTS: creates new job object initialising private fields
    public Job(String jobTitle, String company){
        this.company = company;
        this.jobTitle = jobTitle;
        this.dateApplied = LocalDate.now(); //used Java's function.
        this.jobStatus = "Applied";  //hardcoding it currently.
        this.dateLastChanged = dateApplied;
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
    public LocalDate getDateApplied(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("L dd yyyy");
        String datechanged = dateApplied.format(formatter);
        LocalDate parsedDate = LocalDate.parse(datechanged, formatter);
        return parsedDate; }

    // MODIFIES: this
    // EFFECTS: updates the date of change.
    public void setDateLastChanged() {
        this.dateLastChanged = LocalDate.now();
    }

    //EFFECTS: return the date of last changed
    public LocalDate getDateLastChanged() {
        //info from oracle documentation: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("L dd yyyy");
        String datechanged = dateLastChanged.format(formatter);
        LocalDate parsedDate = LocalDate.parse(datechanged, formatter);
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

}
