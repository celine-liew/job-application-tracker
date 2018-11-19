package model;

import Interfaces.JobInterface;
import Interfaces.Savable;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class Save implements Savable {

    PrintWriter writer;
    // private List<Job> jobs;

    public Save(String filename) throws IOException {
        writer = new PrintWriter(filename, "UTF-8");
    }

    public void writeFile(List<Job> jobs){
        //parsed info
        try {
            for (JobInterface job : jobs) {
                writer.append(Integer.toString(job.getJobID()));
                addComa();
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
}