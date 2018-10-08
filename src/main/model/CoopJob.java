package model;

public class CoopJob extends Job {

    public CoopJob(String jobType, String jobTitle, String company) {
        super(jobTitle, company);
        this.jobType = "Coop";
    }

    public void printApplied(){
        System.out.println("Coop Job applied! Good Luck!");
    }

}
