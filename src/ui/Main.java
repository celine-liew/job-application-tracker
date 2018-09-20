package ui;

import model.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // arrayList storing job objects
    // arrayList storing edit history

    List<Job> jobs = new ArrayList<>();

    public Main() {
        Scanner scanner = new Scanner(System.in);
        String input;
        String inputJobTitle;
        String inputCompany;
        String inputEdit;
        while (true) {
            System.out.println("What would you life to do: \n(1) Add new Job application.\n(2) Update job application status.");
            input = scanner.nextLine();

            if (input.equals("1")) {
                System.out.println("you selected: " + input + " - Add new job.");
                System.out.println("Enter job title:");
                inputJobTitle = scanner.nextLine();
                System.out.println("Enter company applied:");
                inputCompany = scanner.nextLine();

                addJob(inputJobTitle, inputCompany);
                System.out.println("Job applied! Good Luck!");

            } else if (input.equals("2")) {
                System.out.println("you selected: " + input + " - Update job application status.");
                System.out.println();
                printJobs();
                System.out.println();
                System.out.println("Select job # to edit.");
                inputEdit = scanner.nextLine();
                int edit = Integer.parseInt(inputEdit);
                statusUpdate(edit);

                //get user input to choose which to edit
                //edit object fields selected
            }
        }
    }


    public void addJob(String jobTitle, String company) {
        Job job = new Job(jobTitle, company);
        jobs.add(job);
    }

    public void printJobs() {
        int idx = 0;
        for (Job job: jobs) {
            System.out.println(idx + " " + job.getJobTitle() + " " + job.getCompany() + " Applied:" + job.getDateApplied());
            idx++;
        }
    }


    public void statusUpdate(int i) {
        Job job = jobs.get(i);
        System.out.println("You are updating the following job entry:\n"+
                i + " "+ job.getJobTitle() + " " + job.getCompany() + " Applied:" + job.getDateApplied());
        System.out.println();
        //get user input to choose what status to update
        System.out.println("Job Application Status Updated!\n");

    }

    public static void main(String[] args) { new Main();  }
}
