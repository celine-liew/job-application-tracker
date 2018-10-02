package Interfaces;

import model.Job;

import java.util.List;

public interface Savable {

    public void writeFile(List<Job> jobs);

}
