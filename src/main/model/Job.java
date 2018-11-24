package model;

import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;
import Interfaces.JobInterface;
import Interfaces.OrgListInterface;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html


public abstract class Job implements JobInterface {

    // protected String jobType;
    // protected String jobTitle; //private so that it is not visible outside of this Class. All operations done by getters/setters.
    // protected String company;
    // public LocalDate dateApplied; //Changed to date time function in Java. Set to public for testing.
    // protected String jobStatus;
    // protected LocalDate dateLastChanged;
    public final String APPLIED = "Applied";
    // protected String coopDuration;
    // protected int jobID;
    private List<OrgListInterface> listBelongs = new ArrayList<>();
    protected final String FULL_TIME = "Full-time";
    protected final String CO_OP = "Coop";
    private String loaded;

    private IntegerProperty jobID;
    private StringProperty jobType;
    private StringProperty jobTitle;
    private StringProperty company;
    private StringProperty dateApplied;
    private StringProperty dateLastChanged;
    private StringProperty jobStatus;
    private StringProperty coopDuration;

    // REQUIRES: jobTitle and company to be valid
    // MODIFIES: this
    // EFFECTS: creates new job object initialising private fields
    public Job(int jobID, String jobTitle, String company) throws InvalidEntryException {
        this.jobIDProperty().set(jobID);
        // this.jobType = "";
        this.jobTypeProperty().set("");
        // this.jobTitle = jobTitle;
        this.jobTitleProperty().set(jobTitle);
        //this.company = company;
        this.companyProperty().set(company);
        // this.dateApplied = LocalDate.now(); //used Java's function.
        this.dateAppliedProperty().set(LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd uuuu")));
        // this.dateLastChanged = dateApplied;
        this.dateLastChangedProperty().set(this.getDateApplied());
        // this.jobStatus = APPLIED;  //hardcoding it currently.
        this.jobStatusProperty().set(APPLIED);
        // this.coopDuration = "n/a";
        this.coopDurationProperty().set("n/a");
    }

    abstract void printApplied();

    public int getJobID() {
        return jobIDProperty().get();
    }

    public IntegerProperty jobIDProperty() {
        if (this.jobID == null) {
            this.jobID = new SimpleIntegerProperty(this, "jobID");
        }
        return this.jobID;
    }

    public String getJobType() {
        return jobTypeProperty().get();
    }

    public StringProperty jobTypeProperty() {
        if (this.jobType == null) {
            this.jobType = new SimpleStringProperty(this, "jobType");
        }
        return this.jobType;
    }


    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns the job title
    public String getJobTitle() {
        return jobTitleProperty().get();
    }

    public StringProperty jobTitleProperty() {
        if (this.jobTitle == null) {
            this.jobTitle = new SimpleStringProperty(this, "jobTitle");
        }
        return this.jobTitle;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns company string
    public String getCompany() {
        return companyProperty().get();
    }

    public StringProperty companyProperty() {
        if (company == null) {
            company = new SimpleStringProperty(this, "company");
        }
        return this.company;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns the date the job is applied
    public String getDateApplied() {
        return this.dateAppliedProperty().get();
    }

/*
    public String getDateApplied() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
//        String datechanged = dateApplied.format(formatter);
//        LocalDate parsedDate = LocalDate.parse(datechanged, formatter);
        String parsedDate = dateApplied.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
        return parsedDate;
    } //Have to change this to Property
*/

    public StringProperty dateAppliedProperty() {
        if (this.dateApplied == null) {
            this.dateApplied = new SimpleStringProperty(this, "dateApplied");
        }
        return this.dateApplied;
    }

    // MODIFIES: this
    // EFFECTS: updates the date of change.
    public void setDateLastChanged() {
        this.dateLastChangedProperty().set(LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd uuuu")));
    }

    //EFFECTS: return the date of last changed
    public String getDateLastChanged() {
        //info from oracle documentation: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        // String parsedDate = dateLastChanged.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
        return this.dateLastChangedProperty().get();
    }

    public StringProperty dateLastChangedProperty() {
        if (this.dateLastChanged == null) {
            this.dateLastChanged = new SimpleStringProperty(this, "dateLastChanged");
        }
        return this.dateLastChanged;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns true
    public String getJobStatus() {
        return this.jobStatusProperty().get();
    }

    // REQUIRES: job status that is not the current one.
    // MODIFIES: jobStatus
    // EFFECTS: sets the job status to a new status
    public void setJobStatus(String jobStatus) {
        this.jobStatusProperty().set(jobStatus);
        this.setDateLastChanged();
    }

    public StringProperty jobStatusProperty() {
        if (this.jobStatus == null){
            this.jobStatus = new SimpleStringProperty(this, "jobStatus");
        }
        return this.jobStatus;
    }

    public String getCoopDuration() {
        return this.coopDurationProperty().get();
    }

    public void setCoopDuration(String coopDuration) {
        this.coopDurationProperty().set(coopDuration);
    }

    public StringProperty coopDurationProperty() {
        if (this.coopDuration == null){
            this.coopDuration = new SimpleStringProperty(this, "coopDuration");
        }
        return coopDuration;
    }

    public void InvalidChoice(int choice) throws InvalidChoiceException, NumberFormatException {
    }

    public void addListBelongs(OrgListInterface list){
        listBelongs.add(list);
    }

    public void removeListBelongs(){
        for (OrgListInterface jli : this.listBelongs){
            jli.removeJob(this);
        }
    }

    //not needed anymore since i deleted colist (reflexive relationship)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(getJobType(), job.getJobType()) &&
                Objects.equals(getJobTitle(), job.getJobType()) &&
                Objects.equals(getCompany(), job.getCompany()) &&
                Objects.equals(getCoopDuration(), job.getCoopDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobType(), getJobTitle(), getCompany(), getCoopDuration());
    }


}
