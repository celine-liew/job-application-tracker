package main.ui;

import model.Job;
import model.JobList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // arrayList storing job objects
    // arrayList storing edit history
    JobList jl = new JobList();

    public Main() {

        Scanner scanner = new Scanner(System.in);
        String input;
        String inputJobTitle;
        String inputCompany;
        while (true) {
            System.out.println("What would you like to do: " +
                    "\n(1) Add new Job application." +
                    "\n(2) Update job application status." +
                    "\n(3) Show jobs list.");
            input = scanner.nextLine();

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
                validJobEntry();

            } else if (input.equals("3")) {
                System.out.println("you selected: " + input + " - Show jobs list.");
                System.out.println();
                printJobs();
                System.out.println();}
            //get user input to choose which to edit
            //edit object fields selected
        }
    }


    public void printJobs() {
        List<Job> jobs = jl.getJobList();
        int idx = 0;
        if (jobs.size() > 0) {
            for (Job job : jl.getJobList()) {
                System.out.println(idx + " " + job.getJobTitle() + " " + job.getCompany() + " Applied:" + job.getDateApplied());
                idx++;
            }
        } else {
            System.out.println("No jobs applied.");
        }
    }

    public void validJobEntry() {
        List<Job> jobs = jl.getJobList();
        String inputEdit;
        Scanner scanner = new Scanner(System.in);
        if (jobs.size() > 0) {
            System.out.println("Select job # to edit.");
            inputEdit = scanner.nextLine();
            int edit = Integer.parseInt(inputEdit);
            statusUpdate(edit);
        }
    }

    public void statusUpdate(int i) {
        List<Job> jobs = jl.getJobList();
        if (i > jobs.size()-1) {
            System.out.println("Please enter valid job entry #.");
        } else {
            System.out.println("You are updating the following job entry:\n" +
                    i + " " + jobs.get(i).getJobTitle() + " " + jobs.get(i).getCompany() + " Applied:" + jobs.get(i).getDateApplied());
            System.out.println();
        }
        //get user input to choose what status to update
        System.out.println("Job Application Status Updated!\n");
    }

    public static void main(String[] args) { new Main(); }
}

