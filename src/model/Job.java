package model;

public class Job {

    private String jobTitle; //private so that it is not visible outside of this Class. All operations done by getters/setters.
    private String company;
    private String dateApplied; //hardcoding the date for now.
    private String jobStatus;

    public Job(String jobTitle, String company){
        this.company = company;
        this.jobTitle = jobTitle;
        this.dateApplied = "today"; //hardcoding the date now.
        this.jobStatus = "Applied";
    }

    public String getJobTitle(){
        return jobTitle;
    }

    public String getCompany(){
        return company;
    }

    public String getDateApplied(){
        return dateApplied;
    }

    public String getJobStatus(){
        return jobStatus;
    }

    public void setJobStatus(String jobStatus){
        this.jobStatus = jobStatus;
    }

}
