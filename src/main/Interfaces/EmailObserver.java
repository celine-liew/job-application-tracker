package Interfaces;

public interface EmailObserver {

    public void updateJob(String operation, String jobType, String jobTitle, String company);

    public void updateJob(String OLD_STATUS, String newStatus,
                          String operation, String jobType, String jobTitle, String company);

}
