package main.ui;

import model.Job;
import model.JobList;
import model.Load;
import Interfaces.Loadable;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // arrayList storing job objects
    // arrayList storing edit history
    JobList jl;
    Scanner scanner = new Scanner(System.in);
    protected static final String filename = "inputFile.csv";

    public Main() throws IOException {

        String input;
        String inputJobTitle;
        String inputCompany;
        Loadable load = new Load(filename);
        load.loadFile();
        jl = new JobList(load.getParsedLines());

        while (true) {
            System.out.println("What would you like to do: " +
                    "\n(1) Add new Job application." +
                    "\n(2) Update job application status." +
                    "\n(3) Show jobs list." +
                    "\n(4) Save and exit.");
            input = scanner.nextLine();

            // 1 - add new job
            // 2 - update job status
            // 3 - show job list
            if (input.equals("1")) {
                System.out.println("you selected: " + input + " - Add new job.");
                System.out.println("Enter job title:");
                inputJobTitle = scanner.nextLine();
                System.out.println("Enter company applied:");
                inputCompany = scanner.nextLine();

                jl.addJob(inputJobTitle, inputCompany);
                System.out.println("Job applied! Good Luck!");
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
                System.out.println();}

            else if (input.equals("4")) {
                jl.saveJobs();
                return;
            }
            //get user input to choose which to edit
            //edit object fields selected
        }
    }

    // REQUIRES: the job list must not be empty
    // EFFECTS: prints the jobs list
    public void printJobs() {
        List<Job> jobs = jl.getJobList();
        int idx = 0;
        if (jobs.size() > 0) {
            for (Job job : jl.getJobList()) {
                System.out.println(idx + " " + job.getJobTitle() + " " + job.getCompany()
                        + " Applied: " + job.getDateApplied()
                        + " Status: " + job.getJobStatus()) ;
                idx++;
            }
        } else {
            System.out.println("No jobs applied.");
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the job status of the job entry
    public void statusUpdate() {
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

    public static void main (String[] args) throws IOException { new Main(); }
}

