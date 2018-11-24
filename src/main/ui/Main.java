package ui;

import Exceptions.Invalid2ChoiceException;
import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidStringException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.util.*;

public class Main extends Application {
    // arrayList storing job objects
    private JobList jl;
    Scanner scanner = new Scanner(System.in);
    protected String filename;
    private String inputJobTitle;
    private String inputCompany;
    boolean retryEntry;
    private CompanyList companyList;
    private String SELECTED = "you selected: ";
    final String FULL_TIME = "Full-time";
    final String CO_OP = "Coop";
    private ReadJobAPI readJobAPI;
    private Map<String, String> jobReco;

//    public Main() throws IOException, InvalidEntryException, JSONException {
//        readJobAPI = new ReadJobAPI();
//        parseData(readJobAPI.retreveData());
//        companyList = new CompanyList();
//        String input;
//        String jobType = "";
//        do {
//            retryEntry = false;
//            System.out.println("input filename to load");
//            filename = scanner.nextLine();
//            Loadable load = new Load(filename);
//            try {
//                load.loadFile();
//                jl = new JobList(companyList, load.getParsedLines());
//                System.out.println("all jobs loaded!");
//            } catch (NullPointerException e) {
//                System.out.println("No file to load.");
//                retryEntry = true;
//            }
//        }
//        while (retryEntry);
//
//        while (true) {
//            mainMenu();
//            input = scanner.nextLine();
//
//            // 1 - add new job -> choose job type.
//            // 2 - update job status
//            // 3 - show job list
//            if (input.equals("1")) {
//                jobChoiceMenu(input);
//                enterJob();
//                filterCoop(jobType, inputJobTitle, inputCompany);
//                System.out.println();
//                printJobs(jl);
//                System.out.println();
//            } else if (input.equals("2")) {
//                System.out.println(SELECTED + input + " - Update job application status.");
//                System.out.println();
//                printJobs(jl);
//                System.out.println();
//                if (!jl.jobLisEmpty()) {
//                    statusUpdate();
//                }
//            } else if (input.equals("3")) {
//                System.out.println(SELECTED+ input + " - Show jobs list.");
//                System.out.println();
//                printJobs(jl);
//                do {
//                    System.out.println("1) return to main menu\n" +
//                            "2) Show jobs by company\n" +
//                            "3) Remove Job.");
//                    try {
//                        input = scanner.nextLine();
//                        invalid3choice(Integer.parseInt(input));
//                        if (input.equals("1")) {
//                            mainMenu();
//                        }
//                        if (input.equals("2")) {
//                            printByCompany();
//                        }
//                        if (input.equals("3")) {
//                            removeJobMenu();
//                        }
//                    } catch (InvalidChoiceException e) {
//                        System.out.println("invalid choice. Try again.");
//                        retryEntry = true;
//                    }
//                    System.out.println();
//                } while (retryEntry);
//            } else if (input.equals("4")) {
//                removeJobMenu();
//            } else if (input.equals("5")) {
//                jl.saveJobs(filename);
//                return;
//            }
//        }
//    }

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
        } else {
            System.out.println("No jobs applied.");
        }
    }

    public void printByCompany() {
        Map<String, List<String>> jobsPrint = companyList.returnJobsbyType();

        for (Map.Entry<String, List<String>> entry : jobsPrint.entrySet()) {
            System.out.println(entry.getKey());
            for (String s : entry.getValue()) {
                System.out.println(s);
            }
        }
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

    // MODIFIES: this
    // EFFECTS: updates the job status of the job entry
    public void statusUpdate() {
        int inputEdit;
        //Collection<Job> jobs = jl.getJobList();
        System.out.println("Select job # to edit.");
        inputEdit = Integer.parseInt(scanner.nextLine());
        while (jl.invalidJoblistRange(inputEdit)) {
            System.out.println("Please enter valid job entry #.");
            System.out.println("Select job # to edit.");
            inputEdit = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Enter new job status:");
        String newStatus = scanner.nextLine();
        jl.newStatusJob(inputEdit,newStatus);
        System.out.println("Job Application Status Updated to: " + newStatus + "!\n");
    }

    public void jobChoiceMenu(String input){
        do {
            retryEntry = false;
            System.out.println(SELECTED + input + " - Add new job.");
            System.out.println("Choose job type:\n" +
                    "(1) Coop\n" +
                    "(2) Full-time");
            try {
                String jobType = scanner.nextLine();
                invalid2choice(Integer.parseInt(jobType));
            } catch (Invalid2ChoiceException e) {
                System.out.println("invalid choice. Try again.");
                retryEntry = true;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + " Try again");
                retryEntry = true;
            }
        } while (retryEntry);
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

    public void filterCoop(String jobType, String jobTitle, String company) throws InvalidEntryException {
        String coopTerm;
        if (jobType.equalsIgnoreCase("1")) {
            do {
                jobType = FULL_TIME;
                retryEntry = false;
                System.out.println("Select coop duration:\n" +
                        "1) 4 months\n" +
                        "2) 8 months\n" +
                        "3) 1 year.");
                try {
                    coopTerm = scanner.nextLine();
                    jl.addJob(jobType, jobTitle, company, coopTerm);
                } catch (NumberFormatException e) {
                    System.out.println("Not a number.");
                    retryEntry = true;
                }

            } while (retryEntry);


        } else {
            jobType = CO_OP;
            jl.addJob(jobType, jobTitle, company, "n/a");
        }
    }

    public void removeJobMenu() {
        do {
            retryEntry = false;
            printJobs(jl);
            System.out.println("Please select ID of job to delete:");
            try {
                int delete = Integer.parseInt(scanner.nextLine());
                jl.removeJob(delete);
                System.out.println("job removed! Updated job list:\n");
                printJobs(jl);
            } catch (NumberFormatException e) {
                System.out.println("invalid input. Try again.");
                retryEntry = true;
            } catch (NullPointerException e) {
                System.out.println("invalid. Try again");
                retryEntry = true;
            }
        } while (retryEntry);
    }

    public void parseData(Map jobReco) {
        System.out.println("You may be interested in the following job posted today: ");
        System.out.println("Job type: " + jobReco.get("type"));
        System.out.println("Title: " + jobReco.get("title"));
        System.out.println("Company: "+ jobReco.get("company"));
        System.out.println("Location: "+ jobReco.get("location"));
        System.out.println("Apply link/email: " + jobReco.get("url"));
        System.out.println();
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

//    public void invalid3choice(int inter) throws InvalidChoiceException, NumberFormatException {
//        if (inter == 0 || inter > 3) {
//            throw new InvalidChoiceException();
//        }
//        return;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        primaryStage.setTitle("Job Application Database");
        primaryStage.setScene(new Scene(root, 800, 600));
        //primaryStage.setScene(label());
        primaryStage.show();


    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }


}

