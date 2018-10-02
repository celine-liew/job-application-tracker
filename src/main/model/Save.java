package model;

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

}