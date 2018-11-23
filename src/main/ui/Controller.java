package ui;

import Exceptions.InvalidEntryException;
import Interfaces.Loadable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

//reference on tableView: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html
public class Controller implements Initializable {

    private JobList jl;
    //Scanner scanner = new Scanner(System.in);
    private String fileName;

    ObservableList<Job> jobs;

    @FXML
    TableView<Job> tableView;

    @FXML
    ChoiceBox<String> jobType;

    @FXML
    ChoiceBox<String> coopDuration;

    @FXML
    Button addJobButton;

    @FXML
    TextField jobTitleField;

    @FXML
    TextField companyField;

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

        jobs = FXCollections.observableArrayList(jl.getJobList());


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
        jobStatusCol.setCellFactory(TextFieldTableCell.<Job>forTableColumn());
        jobStatusCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Job, String> t) -> {
                    ((Job) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setJobStatus(t.getNewValue());
                });

        TableColumn<Job, String> coopDurationCol = new TableColumn<>("Coop Duration");
        coopDurationCol.setCellValueFactory(new PropertyValueFactory("coopDuration"));
        tableView.setEditable(true);
        tableView.setItems(jobs);
        tableView.getColumns().setAll(
                jobIDCol,
                jobTypeCol,
                jobTitleCol,
                companyCol,
                dateAppliedCol,
                jobStatusCol,
                coopDurationCol,
                dateLastChangedCol);

        jobType.setItems(FXCollections.observableArrayList("Coop", "Full-time"));
        coopDuration.setItems(FXCollections.observableArrayList("4 Month", "8 Month", "1 Year"));
    }

    @FXML
    public void addJob() throws InvalidEntryException {
        System.out.println(companyField.getText());
        System.out.println(jobTitleField.getText());
        System.out.println(jobType.getValue());
        System.out.println(coopDuration.getValue());
        coopDuration.setDisable(true);
        jl.addJob(jobType.getValue(), jobTitleField.getText(), companyField.getText(), "n/a");
        jobs = FXCollections.observableArrayList(jl.getJobList());
        tableView.setItems(jobs);
    }
}
