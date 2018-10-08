package model;

public class FulltimeJob extends Job {

    public FulltimeJob(String jobType, String jobTitle, String company) {
        super(jobTitle, company);
        this.jobType = "Full-time";
    }

    public void printApplied(){
        System.out.println("Full-time Job applied! Get the Job!");
    }

}
