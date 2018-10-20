package model;

import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;

public class CoopJob extends Job {

    public final String CO_OP = "Coop";
    private String coopDuration;
    private int coopTerm;

    //EFFECTS: Create a new coop job, with coop-term length
    public CoopJob(String jobTitle, String company) throws InvalidEntryException {
        super(jobTitle, company);


        this.jobType = CO_OP;
        coopDuration = "";
    }

    public void printApplied(){
        System.out.println("Coop Job applied! Good Luck!");
    }

    @Override
    public void InvalidChoice(int coopTerm) throws InvalidChoiceException, NumberFormatException {
        if (coopTerm == 0 || coopTerm >= 4) {
            throw new InvalidChoiceException();
        }
        return;
    }


    public void setCoopDuration (int coopTerm){
        this.coopTerm = coopTerm;
        switch (coopTerm) {
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
