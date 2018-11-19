package ui;

import Exceptions.InvalidEntryException;
import Interfaces.Loadable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    TableView<Job> tableView;

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

        ObservableList<Job> jobs = FXCollections.observableArrayList(jl.getJobList());
        tableView.setItems(jobs);

        TableColumn<Job, String> jobIDCol = new TableColumn<>("Job ID");
        jobIDCol.setCellValueFactory(new PropertyValueFactory("jobID"));

        TableColumn<Job, String> jobTypeCol = new TableColumn<>("Job Type");
        jobTypeCol.setCellValueFactory(new PropertyValueFactory("jobType"));

        TableColumn<Job, String> jobTitleCol = new TableColumn<>("Job Title");
        jobTitleCol.setCellValueFactory(new PropertyValueFactory("jobTitle"));

        TableColumn<Job, String> companyCol = new TableColumn<>("Company");
        companyCol.setCellValueFactory(new PropertyValueFactory("company"));

        TableColumn<Job, String> dateAppliedCol = new TableColumn<>("Date Applied");
        dateAppliedCol.setCellValueFactory(new PropertyValueFactory("dateApplied"));

        TableColumn<Job, String> dateLastChangedCol = new TableColumn<>("Last Changed");
        dateLastChangedCol.setCellValueFactory(new PropertyValueFactory("dateLastChanged"));

        TableColumn<Job, String> jobStatusCol = new TableColumn<>("Job Status");
        jobStatusCol.setCellValueFactory(new PropertyValueFactory("jobStatus"));

        TableColumn<Job, String> coopDurationCol = new TableColumn<>("Coop Duration");
        coopDurationCol.setCellValueFactory(new PropertyValueFactory("coopDuration"));

        tableView.getColumns().setAll(
                jobIDCol,
                jobTypeCol,
                jobTitleCol,
                companyCol,
                dateAppliedCol,
                dateLastChangedCol,
                jobStatusCol,
                coopDurationCol
        );
    }

    @FXML
    public void addItem() {
        items.add("asdfds");
    }
}
