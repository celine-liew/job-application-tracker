package main.ui;

import Exceptions.Invalid2ChoiceException;
import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidStringException;
import model.Job;
import model.JobList;
import Interfaces.Loadable;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
public class Main {

    // arrayList storing job objects
    // arrayList storing edit history
    JobList jl;
    Scanner scanner = new Scanner(System.in);
    protected String filename = "";
    String inputJobTitle;
    String inputCompany;
    boolean retryEntry;
    Loadable load;

    public Main() throws Exception  {

        String input;
        String jobType = "";
        do {
            retryEntry = false;
            System.out.println("input filename to load");
            filename = scanner.nextLine();
            try {
                load = new JobList(filename);
            } catch (Exception e) {
                System.out.println("filename not found.");
                retryEntry = true;
            } finally {
                System.out.println("Enter valid file! Try again.");
            }
        }
            while (retryEntry);
            //load.loadFile();
            jl = new JobList(load.getParsedLines());

        while (true) {
            System.out.println();
            System.out.println("What would you like to do: " +
                    "\n(1) Add new Job application." +
                    "\n(2) Update job application status." +
                    "\n(3) Show jobs list." +
                    "\n(4) Save and exit.");
            input = scanner.nextLine();

            // 1 - add new job -> choose job type.
            // 2 - update job status
            // 3 - show job list
            if (input.equals("1")) {
                do {
                    retryEntry = false;
                    System.out.println("you selected: " + input + " - Add new job.");
                    System.out.println("Choose job type:\n" +
                            "(1) Coop\n" +
                            "(2) Full-time");
                    try {
                        jobType = scanner.nextLine();
                        invalidJobchoice(Integer.parseInt(jobType));
                    } catch (Invalid2ChoiceException e) {
                        System.out.println("invalid choice. Try again.");
                        retryEntry = true;
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage() + " Try again");
                        retryEntry = true;
                    } finally {
                        System.out.println("choose coop or full time");
                    }
                } while (retryEntry);
                enterJob();
                jl.addJob(jobType, inputJobTitle, inputCompany);
                System.out.println();
                printJobs();
                System.out.println();
            } else if (input.equals("2")) {
                System.out.println("you selected: " + input + " - Update job application status.");
                System.out.println();
                printJobs();
                System.out.println();
                if (!jl.jobLisEmpty()) {
                    statusUpdate();
                }
            } else if (input.equals("3")) {
                System.out.println("you selected: " + input + " - Show jobs list.");
                System.out.println();
                printJobs();
                System.out.println();
            } else if (input.equals("4")) {
                jl.saveJobs(filename);
                return;
            }
            //get user input to choose which to edit
            //edit object fields selected
        }
    }

    // REQUIRES: the job list must not be empty
    // EFFECTS: prints the jobs list
    public void printJobs(){
        List<Job> jobs = jl.getJobList();
        int idx = 0;
        if (jobs.size() > 0) {
            for (Job job : jl.getJobList()) {
                System.out.println(idx + " " +
                        "" + job.getJobType() + " "
                        + job.getJobTitle() + " " + job.getCompany()
                        + " Applied: " + job.getDateApplied()
                        + " Status: " + job.getJobStatus()
                        + " CoopDuration: " + job.getCoopDuration());
                idx++;
            }
        } else {
            System.out.println("No jobs applied.");
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the job status of the job entry
    public void statusUpdate(){
        int inputEdit;
        List<Job> jobs = jl.getJobList();
        System.out.println("Select job # to edit.");
        inputEdit = Integer.parseInt(scanner.nextLine());
        while (jl.invalidJoblistRange(inputEdit)) {
            System.out.println("Please enter valid job entry #.");
            System.out.println("Select job # to edit.");
            inputEdit = Integer.parseInt(scanner.nextLine());
        }
        Job jobtoedit = jl.getJob(inputEdit);
        System.out.println("Enter new job status:");
        String newStatus = scanner.nextLine();
        jobtoedit.setJobStatus(newStatus);
        jobtoedit.setDateLastChanged();
        System.out.println("Job Application Status Updated to: " + newStatus + "!\n");
    }

    public void enterJob(){
        System.out.println("Enter job title:");
        do {
            retryEntry = false;
            inputJobTitle = scanner.nextLine();
            try {
                stringBlank(inputJobTitle);
                System.out.println("Enter company applied:");
            } catch (InvalidStringException e) {
                System.out.println("Enter valid job entry:");
                retryEntry = true;
            }
        } while (retryEntry);
        do {
            retryEntry = false;
            try {inputCompany = scanner.nextLine();
                stringBlank(inputCompany);
            } catch (InvalidStringException e) {
                System.out.println("Enter valid company entry:");
                retryEntry = true;
            }
        } while (retryEntry);

    }

    public void stringBlank(String checkblank) throws InvalidStringException {
        if (checkblank.trim().length() == 0) {
            throw new InvalidStringException();
        }
    }

    public void invalidJobchoice(int Jobtype) throws Invalid2ChoiceException, NumberFormatException {
        if (Jobtype == 0 || Jobtype > 2 ) {
            throw new Invalid2ChoiceException();
        }
        return;
    }


    public static void main (String[] args) throws Exception { new Main(); }
}

