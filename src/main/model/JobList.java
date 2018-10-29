package model;

import Exceptions.InvalidEntryException;
import Interfaces.JoblistInterface;
import Interfaces.Loadable;
import Interfaces.Savable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JobList implements Savable, Loadable, JoblistInterface {

    protected HashMap<Integer, Job> jobs = new HashMap<>();
    //protected HashMap<String, Job> jobs;
    PrintWriter writer;
    private List<String> lines;
    private List<List> parsedLines = new ArrayList<>();
    private Job restorejob;
    Scanner scanner = new Scanner(System.in);
    boolean retryEntry;
    int maxID;
    int jobID; //NEEDS TO BE IN THE WRITE FILE.
    private Job job;

    private CompanyList colist;// = new CompanyList();

    public JobList(CompanyList companyList, String filename) throws IOException {
        loadFile(filename);
        this.colist = companyList;
    }

    public JobList(CompanyList companyList, List<List> jobLists) throws InvalidEntryException {
        //jobs = new HashMap<>();
        this.colist = companyList;
        for (List<String> l : jobLists) {
            restoreJob(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5), l.get(6), l.get(7));
        }
        restorejob.printApplied();
    }

    //  public JobList() {
////        jobs = new ArrayList<>();
////    }
    public JobList(CompanyList companyList) {
        jobs = new HashMap<>();
        this.colist = companyList;
    }

    public void saveJobs(String filename) throws IOException, NullPointerException {
        Save(filename);
    }

    public void restoreJob(String jobID, String jobType, String jobTitle, String company, String dateApplied, String jobStatus, String dateLastChanged, String coopDuration) throws InvalidEntryException {
        restorejob = new allJob(jobID, jobType, jobTitle, company, dateApplied, jobStatus, dateLastChanged, coopDuration);
        int jobInt = Integer.parseInt(jobID);
        jobs.put(jobInt, restorejob);
        colist.addJob(restorejob);
        if (jobInt > maxID)
            maxID = jobInt;
    }

    // REQUIRES: jobTitle, company
    // MODIFIES: this
    // EFFECTS: creates a new job and add to job list.
    public void addJob(String jobType, String jobTitle, String company) throws InvalidEntryException {
        String coopTerm;
        if (jobType.equalsIgnoreCase("1")) {
            jobID = maxID + 1;
            maxID++;
            job = new CoopJob(jobID, jobTitle, company); //TODO

            do {
                retryEntry = false;
                System.out.println("Select coop duration:\n" +
                        "1) 4 months\n" +
                        "2) 8 months\n" +
                        "3) 1 year.");
                try {
                    coopTerm = scanner.nextLine();
                    job.setCoopDuration(coopTerm);
                } catch (NumberFormatException e) {
                    System.out.println("Not a number.");
                    retryEntry = true;
                } finally {
                    System.out.println("Try again:");
                }

            } while (retryEntry);


        } else {
            jobID = maxID + 1;
            maxID++;
            job = new FulltimeJob(jobID, jobTitle, company);

        }
        jobs.put(jobID, job);
        colist.addJob(job);
        job.printApplied();
    }

    // REQUIRES: i is within job list range
    // MODIFIES: nothing
    // EFFECTS: return job in given index of job list.
    public Job getJob(int i) {
        return jobs.get(i);
    }

    //EFFECTS: return true is job list is empty.
    public boolean jobLisEmpty() {
        return jobs.isEmpty();
    }

    //EFFECT: return job list
    public List<Job> getJobList() {
        List<Job> jobsPrint = new ArrayList<>();
        for (Map.Entry<Integer, Job> entry : jobs.entrySet()) {
            jobsPrint.add(entry.getValue());
        }
        return jobsPrint;
    }


    // EFFECTS: return true if input index is not within Job list range
    public boolean invalidJoblistRange(int i) {
        if (i > jobs.size() - 1) {
            return true;
        }
        return false;
    }

    @Override
    public void removeJob(int i) {
        jobs.get(i).removeListBelongs();
        jobs.remove(i);
    }

    @Override
    public void removeJob(Job job) {

    }


    public void Save(String filename) throws IOException {
        writer = new PrintWriter(filename, "UTF-8");
        List<Job> jobsSave = new ArrayList<>();
        for (Map.Entry<Integer, Job> entry : jobs.entrySet()) {
            jobsSave.add(entry.getValue());
        }
        writeFile((List<Job>) jobsSave);
    }

    public void writeFile(List<Job> jobs) {
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

    public void loadFile(String filename) {
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            System.out.println("invalid file name");
        } catch (NullPointerException e) {
            System.out.println("no file to load");
        }
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnComma(line);
            parsedLines.add(partsOfLine);
            System.out.println("jobID: " + partsOfLine.get(0) + " ");
            System.out.print("jobType: " + partsOfLine.get(1) + " ");
            System.out.print("jobTitle: " + partsOfLine.get(2) + " ");
            System.out.print("company: " + partsOfLine.get(3) + " ");
            System.out.print("dateApplied: " + partsOfLine.get(4) + " ");
            System.out.print("jobStatus: " + partsOfLine.get(5) + " ");
            System.out.println("dateLastChanged: " + partsOfLine.get(6));
            System.out.println("coopDuration: " + partsOfLine.get(7));
        }
    }

    public List<List> getParsedLines() {
        return this.parsedLines;
    }


    public ArrayList<String> splitOnComma(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }
    public void addCompany() {
    }

    }


