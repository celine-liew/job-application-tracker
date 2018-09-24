package model;

public class Job {

    private String jobTitle; //private so that it is not visible outside of this Class. All operations done by getters/setters.
    private String company;
    private String dateApplied; //hardcoding the date for now.
    private String jobStatus;

    // REQUIRES: jobTitle and company
    // MODIFIES: this
    // EFFECTS: creates new job object initialising private fields
    public Job(String jobTitle, String company){
        this.company = company;
        this.jobTitle = jobTitle;
        this.dateApplied = "today"; //hardcoding the date now.
        this.jobStatus = "Applied";
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
        return dateApplied;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns true
    public String getJobStatus(){
        return jobStatus;
    }

    // REQUIRES: job status
    // MODIFIES: jobStatus
    // EFFECTS: sets the job status to a new status
    public void setJobStatus(String jobStatus){
        this.jobStatus = jobStatus;
    }

}
