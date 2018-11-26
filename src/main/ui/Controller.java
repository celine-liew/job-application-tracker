package ui;

import Exceptions.InvalidEntryException;
import Interfaces.Loadable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

//reference on tableView: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html
//reference for ChoiceBox: https://docs.oracle.com/javafx/2/ui_controls/choice-box.htm
//reference for TextField: https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
public class Controller implements Initializable {
    final String FULL_TIME = "Full-time";
    final String CO_OP = "Coop";

    private JobList jl;
    //Scanner scanner = new Scanner(System.in);
    private String fileName = new String("inputFile.csv");;

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

    @FXML
    Label label;

    @FXML
    Label apiJobType;

    @FXML
    Label apiTitle;

    @FXML
    Label apiCompany;

    @FXML
    Label apiLocation;

    @FXML
    Label apiApplyLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jl = new JobList();

        tableView.setPlaceholder(new Label("Add a new job from below or load existing jobs from file"));

        // disable coop when fulltime selected
        // https://www.youtube.com/watch?v=WZGyP57IH6M
        jobType.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            if (newValue.equals(FULL_TIME)) {
                coopDuration.getSelectionModel().clearSelection();
                coopDuration.setDisable(true);
            } else {
                coopDuration.setDisable(false);
            }
        });


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

        loadToTable();
        setLabel(10);

        tableView.getColumns().setAll(
                jobIDCol,
                jobTypeCol,
                jobTitleCol,
                companyCol,
                dateAppliedCol,
                jobStatusCol,
                coopDurationCol,
                dateLastChangedCol);

        jobType.setItems(FXCollections.observableArrayList(CO_OP, FULL_TIME));
        coopDuration.setItems(FXCollections.observableArrayList("4 Month", "8 Month", "1 Year"));

        ReadJobAPI readJobAPI;
        Map<String, String> jobReco;
        readJobAPI = new ReadJobAPI();
        jobReco = readJobAPI.retreveData();

        apiJobType.setText(jobReco.get("type"));
        apiTitle.setText(jobReco.get("title"));
        apiCompany.setText(jobReco.get("company"));
        apiLocation.setText(jobReco.get("location"));
        apiApplyLink.setText(jobReco.get("url"));
    }

    @FXML
    public void addJob() throws InvalidEntryException {
//        System.out.println(companyField.getText());
//        System.out.println(jobTitleField.getText());
//        System.out.println(jobType.getValue());
//        System.out.println(coopDuration.getValue());

        if (companyField.getText().length() <= 1 || jobTitleField.getText().length() <= 1){
            setLabel(1);
        }
        else if (jobType.getValue() == null) {
            setLabel(2);
        } else if (jobType.getValue().equals(CO_OP)){
            if (coopDuration.getValue() == null) {
                setLabel(5);
            } else {
                // System.out.println(coopDuration.getValue());
                jl.addJob(jobType.getValue(), jobTitleField.getText(), companyField.getText(), coopDuration.getValue());
                loadToTable(); //have to reset this everytime the addJob is executed. abstracted to below.
            }
        }
        else {
            System.out.println(jobType.getValue());
            jl.addJob(jobType.getValue(), jobTitleField.getText(), companyField.getText(), "n/a");
            loadToTable(); //have to reset this everytime the addJob is executed. abstracted to below.
        }
    }


    public void load() {
        try {
            Loadable load = new Load(fileName);
            load.loadFile();
            jl = new JobList(load.getParsedLines());
        } catch (IOException | NullPointerException e) {
            System.out.println("No file to load.");
        } catch (InvalidEntryException e) {
            System.out.println("Invalid Entry Exception");
        }

        loadToTable();
    }

    public void save() throws IOException {
        if (!jl.jobLisEmpty()){
            jl.saveJobs(fileName);
        }
    }

    public void loadToTable() {
        jobs = FXCollections.observableArrayList(jl.getJobList());
        tableView.setItems(jobs);
    }

    public void setLabel(int textPrint) {
        if (textPrint == 1) {
            label.setText("Please enter valid job title and company.");
        } else if (textPrint == 2) {
            label.setText("Please select job type");
        } else if (textPrint == 3) {
            label.setText("Job deleted!!");
        } else if (textPrint == 4) {
            label.setText("Jobs Saved!!");
        } else if (textPrint == 5) {
            label.setText("Enter coop duration");
        } else {
            label.setText("");
        }
        label.setFont(Font.font("Helvetica", 16));
    }

    public void deleteJob(){
        System.out.println("delete");
        Job selectedJob = tableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedJob.getJobID());
        jl.removeJob(selectedJob.getJobID());
        loadToTable();
        setLabel(3);
    }
}
