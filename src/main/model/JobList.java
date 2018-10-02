package model;

import Interfaces.Loadable;
import Interfaces.Savable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobList implements Savable, Loadable {

    protected List<Job> jobs = new ArrayList<>();
    protected String filename = "inputFile.csv";
    PrintWriter writer;
    private List<String> lines;
    private List<List> parsedLines = new ArrayList<>();


    public JobList(String filename) throws Exception{
        loadFile();
    }

    public JobList(List<List> jobLists) {
        for (List<String> l : jobLists) {
            restoreJob(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4));
        }
    }

    public JobList() {

    }

   public void saveJobs() throws IOException  {
        Save(filename);
    }
    public void restoreJob(String jobTitle, String company, String dateApplied, String jobStatus, String dateLastChanged) {
        Job job;
        job = new Job(jobTitle, company, dateApplied, jobStatus, dateLastChanged);
        jobs.add(job);
    }

    // REQUIRES: jobTitle, company
    // MODIFIES: this
    // EFFECTS: creates a new job and add to job list.
    public void addJob(String jobTitle, String company) {
        Job job;
        job = new Job(jobTitle, company);
        jobs.add(job);
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
        for(Job job : jobs){
            writer.append(String.valueOf(job.getJobTitle()));
            addComa();
            writer.append(job.getCompany());
            addComa();
            writer.append(job.getDateApplied());
            addComa();
            writer.append(job.getJobStatus());
            addComa();
            writer.append(job.getDateLastChanged());
            writer.append("\n");
        }
        System.out.println("csv file saved!");
        writer.close();
    }

    private String addComa() {
        return String.valueOf(writer.append(","));
    }

    public void loadFile() throws IOException {
        lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnComma(line);
            parsedLines.add(partsOfLine);
            System.out.print("jobTitle: " + partsOfLine.get(0) + " ");
            System.out.print("company: " + partsOfLine.get(1) + " ");
            System.out.print("dateApplied: " + partsOfLine.get(2) + " ");
            System.out.print("jobStatus: " + partsOfLine.get(3) + " ");
            System.out.println("dateLastChanged: " + partsOfLine.get(4));
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
