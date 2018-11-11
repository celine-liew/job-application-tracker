package model;

import Interfaces.EmailObserver;

public class EmailServer implements EmailObserver {

    public EmailServer(){
    }

    @Override
    public void updateJob(String operation, String jobType, String jobTitle, String company){
        System.out.println("Email sent: You have " +operation + " a new "+ jobType +
                " job ["+ jobTitle + "] at: " +company);
    }

    @Override
    public void updateJob(String OLD_STATUS, String newStatus, String operation, String jobType,
                          String jobTitle, String company) {
        System.out.println("Email sent: You have " +operation +" this job: \n"
                + jobType + " job ["+ jobTitle + "] at: " +company +"\n" +
                "Old Status: " + OLD_STATUS + "\n"
                + "New Status: " + newStatus );
    }

}
