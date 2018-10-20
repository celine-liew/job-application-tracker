package model;

import Exceptions.InvalidChoiceException;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidStringException;
import Interfaces.Loadable;
import Interfaces.Savable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JobList implements Savable, Loadable {

    protected List<Job> jobs = new ArrayList<>();
    protected String filename;
    PrintWriter writer;
    private List<String> lines;
    private List<List> parsedLines = new ArrayList<>();
    private allJob restorejob;
    Scanner scanner = new Scanner(System.in);
    private int coopTerm;
    boolean retryEntry;


    public JobList(String filename) throws IOException {
        loadFile(filename);
    }

    public JobList(List<List> jobLists) throws InvalidEntryException {
        for (List<String> l : jobLists) {
            restoreJob(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4),l.get(5), l.get(6));
        }
        restorejob.printApplied();
    }

    public JobList() {

    }

   public void saveJobs(String filename) throws IOException, NullPointerException {
        Save(filename);
    }

    public void restoreJob(String jobType, String jobTitle, String company, String dateApplied, String jobStatus, String dateLastChanged,String coopDuration) throws InvalidEntryException {
        restorejob = new allJob(jobType,jobTitle, company, dateApplied, jobStatus, dateLastChanged, coopDuration);
        jobs.add(restorejob);
    }

    // REQUIRES: jobTitle, company
    // MODIFIES: this
    // EFFECTS: creates a new job and add to job list.
    public void addJob(String jobType, String jobTitle, String company) throws InvalidEntryException {
        Job job;
        if (jobType.equalsIgnoreCase("1")){
            job = new CoopJob(jobTitle, company);

            do {
                retryEntry = false;
                System.out.println("Select coop duration:\n" +
                        "1) 4 months\n" +
                        "2) 8 months\n" +
                        "3) 1 year.");
                try {
                    coopTerm = Integer.parseInt(scanner.nextLine());
                    job.InvalidChoice(coopTerm);
                    ((CoopJob) job).setCoopDuration(coopTerm);
                } catch (InvalidChoiceException e) {
                    System.out.println("Invalid choice.");
                    retryEntry = true;
                } catch (NumberFormatException e) {
                    System.out.println("Not a number.");
                } finally {
                    System.out.println("Try again:");
                }

            } while (retryEntry);


        } else {
            job = new FulltimeJob(jobTitle, company);
        }
        jobs.add(job);
        job.printApplied();
    }

    // REQUIRES: i is within job list range
    // MODIFIES: nothing
    // EFFECTS: return job in given index of job list.
    public Job getJob(int i){
        return jobs.get(i);
    }

    //EFFECTS: return true is job list is empty.
    public boolean jobLisEmpty() {
        return jobs.isEmpty();
    }

    //EFFECT: return job list
    public List<Job> getJobList() {
        return jobs;
    }


    // EFFECTS: return true if input index is not within Job list range
    public boolean invalidJoblistRange(int i) {
        if (i > jobs.size()- 1) {
            return true;
        }
        return false;
    }


    public void Save(String filename) throws IOException {
        writer = new PrintWriter(filename, "UTF-8");
        writeFile(jobs);
    }

    public void writeFile(List<Job> jobs){
        //parsed info
        try {
            for (Job job : jobs) {
                writer.append(String.valueOf(job.getJobType()));
                addComa();
                writer.append(String.valueOf(job.getJobTitle()));
                addComa();
                writer.append(job.getCompany());
                addComa();
                writer.append(job.getDateApplied());
                addComa();
                writer.append(job.getJobStatus());
                addComa();
                writer.append(job.getDateLastChanged());
                addComa();
                writer.append(job.getCoopDuration());
                writer.append("\n");
            }
            System.out.println("csv file saved!");
            writer.close();
    } catch (NullPointerException e) {
        System.out.println("file is null");
    }
    }

    private String addComa() {
        return String.valueOf(writer.append(","));
    }

    public void loadFile(String filename) throws NullPointerException {
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            System.out.println("invalid file name");
        } catch (NullPointerException e) {
            System.out.println("file not found");
        }
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnComma(line);
            parsedLines.add(partsOfLine);
            System.out.print("jobType: " + partsOfLine.get(0) + " ");
            System.out.print("jobTitle: " + partsOfLine.get(1) + " ");
            System.out.print("company: " + partsOfLine.get(2) + " ");
            System.out.print("dateApplied: " + partsOfLine.get(3) + " ");
            System.out.print("jobStatus: " + partsOfLine.get(4) + " ");
            System.out.println("dateLastChanged: " + partsOfLine.get(5));
            System.out.println("coopDuration: " + partsOfLine.get(6));
        }
    }

    public List<List> getParsedLines() {
        return this.parsedLines;
    }


    public ArrayList<String> splitOnComma(String line){
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
