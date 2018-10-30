package main.ui;

import Exceptions.Invalid2ChoiceException;
import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidStringException;
import model.CompanyList;
import model.Job;
import model.JobList;
import Interfaces.Loadable;

import java.io.IOException;
import java.util.*;

public class Main {

    // arrayList storing job objects
    JobList jl;
    Scanner scanner = new Scanner(System.in);
    protected String filename = "";
    String inputJobTitle;
    String inputCompany;
    boolean retryEntry;
    Loadable load;
    Job job;
    CompanyList companyList;

    public Main() throws IOException, InvalidEntryException {
        companyList = new CompanyList();
        String input = "";
        String jobType = "";
        do {
            retryEntry = false;
            System.out.println("input filename to load");
            filename = scanner.nextLine();
            try {
                load = new JobList(companyList, filename);
            } catch (NullPointerException e) {
                System.out.println("No file to load.");
                retryEntry = true;
            }
        }
        while (retryEntry);
        //load.loadFile();
        jl = new JobList(companyList, load.getParsedLines());

        while (true) {
            mainMenu();
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
                        invalid2choice(Integer.parseInt(jobType));
                    } catch (Invalid2ChoiceException e) {
                        System.out.println("invalid choice. Try again.");
                        retryEntry = true;
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage() + " Try again");
                        retryEntry = true;
                    }
                } while (retryEntry);
                enterJob();
                jl.addJob(jobType, inputJobTitle, inputCompany);
                System.out.println();
                printJobs(jl);
                System.out.println();
            } else if (input.equals("2")) {
                System.out.println("you selected: " + input + " - Update job application status.");
                System.out.println();
                printJobs(jl);
                System.out.println();
                if (!jl.jobLisEmpty()) {
                    statusUpdate();
                }
            } else if (input.equals("3")) {
                System.out.println("you selected: " + input + " - Show jobs list.");
                System.out.println();
                printJobs(jl);
                do {
                    System.out.println("1) return to main menu\n" +
                            "2) Show jobs by company\n" +
                            "3) Remove Job.");
                    try {
                        input = scanner.nextLine();
                        invalid3choice(Integer.parseInt(input));
                        if (input.equals("1")) {
                            mainMenu();
                        }
                        if (input.equals("2")) {
                            printByCompany();
                        }
                        if (input.equals("3")) {
                            removeJobMenu();


                        }
                    } catch (InvalidChoiceException e) {
                        System.out.println("invalid choice. Try again.");
                        retryEntry = true;
                    }
                    System.out.println();
                } while (retryEntry);
            } else if (input.equals("4")) {
                removeJobMenu();
            } else if (input.equals("5")) {
                jl.saveJobs(filename);
                return;
            }
            //get user input to choose which to edit
            //edit object fields selected
        }
    }

    // REQUIRES: the job list must not be empty
    // EFFECTS: prints the jobs list
    public void printJobs(JobList jl) {
//        System.out.println(jl);
        //Collection<Job> jobs = jl.getJobList();
        //int idx = 0;
        List<Job> jobs = jl.getJobList();
        if (jobs.size() > 0) {
            for (Job job : jl.getJobList()) {
                System.out.println("jobID:" + job.getJobID() +
                        " " + job.getJobType() + " "
                        + job.getJobTitle() + " " + job.getCompany()
                        + " Applied: " + job.getDateApplied()
                        + " Status: " + job.getJobStatus()
                        + " CoopDuration: " + job.getCoopDuration());
            }

//            for (Job job: jobs) {
//                System.out.println(job +
//                        "" + job.getJobType() + " "
//                        + job.getJobTitle() + " " + job.getCompany()
//                        + " Applied: " + job.getDateApplied()
//                        + " Status: " + job.getJobStatus()
//                        + " CoopDuration: " + job.getCoopDuration());
//                //idx++;
//            }
        } else {
            System.out.println("No jobs applied.");
        }
    }

    public void printByCompany() {
        Map<String, List<String>> jobsPrint = companyList.returnJobsbyCo();

        for (Map.Entry<String, List<String>> entry : jobsPrint.entrySet()) {
            System.out.println(entry.getKey());
            for (String s : entry.getValue()) {
                System.out.println(s);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the job status of the job entry
    public void statusUpdate() {
        int inputEdit;
        Collection<Job> jobs = jl.getJobList();
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

    public void enterJob() {
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
            try {
                inputCompany = scanner.nextLine();
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

    public void invalid2choice(int inter) throws Invalid2ChoiceException, NumberFormatException {
        if (inter == 0 || inter > 2) {
            throw new Invalid2ChoiceException();
        }
        return;
    }

    public void invalid3choice(int inter) throws InvalidChoiceException, NumberFormatException {
        if (inter == 0 || inter > 3) {
            throw new InvalidChoiceException();
        }
        return;
    }


    public static void main(String[] args) throws Exception {
        new Main();
    }


    public void mainMenu() {
        System.out.println();
        System.out.println("What would you like to do: " +
                "\n(1) Add new Job application." +
                "\n(2) Update job application status." +
                "\n(3) Show jobs list." +
                "\n(4) Delete job." +
                "\n(5) Save and exit.");
    }

    public void removeJobMenu() {
        do {
            retryEntry = false;
            printJobs(jl);
            System.out.println("Please select ID of job to delete:");
            try {
                int delete = Integer.parseInt(scanner.nextLine());
                jl.removeJob(delete);
                System.out.println("job removed! Updated joblist:\n");
                printJobs(jl);
            } catch (NumberFormatException e) {
                System.out.println("invalid input. Try again.");
                retryEntry = true;
            } catch (NullPointerException e) {
                System.out.println("invalid. Try again");
                retryEntry = true;
            }
        } while (retryEntry);

        //TODO company list
    }
}

