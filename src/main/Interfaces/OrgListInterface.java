package Interfaces;

import model.Job;

import java.util.List;
import java.util.Map;

public interface OrgListInterface {

    public void addJob(Job job);

    public void removeJob(Job job);

    public Map<String, List<String>> returnJobsbyType();

}
