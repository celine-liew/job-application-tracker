package ui;

import Exceptions.InvalidEntryException;
import Interfaces.Loadable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Controller implements Initializable {

    private JobList jl;
    Scanner scanner = new Scanner(System.in);
    private String fileName;

    ObservableList<String> items;

    @FXML
    ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileName = new String("inputFile.csv");
        try {
            Loadable load = new Load(fileName);
            load.loadFile();
            jl = new JobList(load.getParsedLines());
        } catch (IOException | NullPointerException e) {
            System.out.println("No file to load.");
        } catch (InvalidEntryException e) {
            System.out.println("Invalid Entry Exception");
        }

        List<Job> jobs = jl.getJobList();
        List<String> test= new ArrayList<>();
        if (jobs.size() > 0) {
            for (Job job : jl.getJobList()) {
                test.add(job.getCompany());

                System.out.println("jobID:" + job.getJobID() +
                        " " + job.getJobType() + " "
                        + job.getJobTitle() + " " + job.getCompany()
                        + " Applied: " + job.getDateApplied()
                        + " Status: " + job.getJobStatus()
                        + " CoopDuration: " + job.getCoopDuration());
            }
        } else {
            System.out.println("No jobs applied.");
        }

        // get jobList
        items = FXCollections.observableArrayList(test);
        listView.setItems(items);
    }

    @FXML
    public void addItem() {
        items.add("asdfds");
    }
}
