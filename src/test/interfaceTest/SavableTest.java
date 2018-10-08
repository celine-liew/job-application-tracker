package interfaceTest;

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

    @BeforeEach
    void setUp() throws IOException {
        jobs = new JobList();
        jobs.addJob("Coop","jobtitletest","companytest");
        //save = new Save(filename);
        jobs.loadFile();
    }

    @Test
    void testWriteFile() throws Exception {
        jobs.saveJobs();
        //(jobs.getJobList());
       Loadable testload = new JobList(filename);
       testload.loadFile();
       j1 = new JobList(testload.getParsedLines());
       Job j = j1.getJob(0);
       assertEquals(j.getCompany(),"companytest");
    }

}