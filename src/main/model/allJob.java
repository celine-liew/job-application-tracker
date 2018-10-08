package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class allJob extends Job {

    public allJob(String jobType, String jobTitle, String company,
                  String dateApplied, String jobStatus, String dateLastChanged) {
        //super(jobType,jobTitle, company, dateApplied, jobStatus, dateLastChanged);
        super(jobTitle, company);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
        this.dateApplied = LocalDate.parse(dateApplied, formatter); //used Java's function.
        this.dateLastChanged = LocalDate.parse(dateLastChanged, formatter);
    }

    public void printApplied(){
        System.out.println("All jobs loaded!!!! ");
    }
}
