package model;

public class CoopJob extends Job {

    public final String CO_OP = "Coop";

    //EFFECTS: Create a new coop job
    public CoopJob(String jobType, String jobTitle, String company) {
        super(jobTitle, company);
        this.jobType = CO_OP;
    }

    public void printApplied(){
        System.out.println("Coop Job applied! Good Luck!");
    }

}
