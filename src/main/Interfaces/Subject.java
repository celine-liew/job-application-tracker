package Interfaces;

import model.Job;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject{

    protected List<EmailObserver> observers = new ArrayList<>();

    public void addObserver(EmailObserver emailObserver){
        if (!observers.contains(emailObserver)){
            observers.add(emailObserver);
        }
    }

    public void notifyEmailObserver(String operation, Job job) {
        for (EmailObserver emailObserver: observers){
            emailObserver.updateJob(operation, job.getJobType(), job.getJobTitle(),job.getCompany());
        }
    }

    public void notifyEmailObserver(String oldstatus, String newStatus,  String operation, Job job) {
        for (EmailObserver emailObserver: observers){
            emailObserver.updateJob(oldstatus, newStatus, operation, job.getJobType(),
                    job.getJobTitle(),job.getCompany());
        }
    }



}
