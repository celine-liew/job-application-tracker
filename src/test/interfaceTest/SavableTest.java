package interfaceTest;

import Exceptions.InvalidEntryException;
import Interfaces.Loadable;
import Interfaces.Savable;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class SavableTest {

    PrintWriter writer;
    String filename = "testFile.csv";
    //Savable save;
    JobList jobs;
    JobList j1;
    private CompanyList companyList;

    @BeforeEach
    void setUp() throws IOException, InvalidEntryException  {
        Loadable load = new Load(filename);
        companyList = new CompanyList();

        load.loadFile();
        jobs = new JobList(companyList, load.getParsedLines());

        //save = new Save(filename);

    }

    @Test
    void testWriteFile() {
//        Job coopJob = new CoopJob("intern","company");
//        ((CoopJob) coopJob).setCoopDuration("1 year");
        try {
            jobs.addJob("2", "job1", "company", "n/a");
            jobs.saveJobs(filename);
            //(jobs.getJobList());

            Loadable testload = new Load(filename);
            testload.loadFile();
            j1 = new JobList(companyList, testload.getParsedLines());
            Job j = j1.getJob(1);
            assertEquals(j.getCompany(), "company");
        } catch (Exception e) {
            fail("Exception thrown when shouldn't");
        }
    }

}