package interfaceTest;

import Exceptions.InvalidEntryException;
import model.CompanyList;
import model.Job;
import model.JobList;
//import model.Load;
import Interfaces.Loadable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadableTest {

    public String filename;
    Loadable testload;
    JobList j1;
    CompanyList companyList;

    @BeforeEach
    void setUp() throws Exception {
        filename = "testFile.csv";
        companyList = new CompanyList();
        //load = new JobList(filename);

    }

    @Test
    void testLoadFilenoException() {
        try {
            testload = new JobList(companyList, filename);
            testload.loadFile(filename);
            j1 = new JobList(companyList, testload.getParsedLines());
            Job j = j1.getJob(1);
            assertEquals(j.getCompany(), "company");
        } catch (Exception e) {
            fail("Exception thrown when shouldn't");
        }
    }

    // When filename is invalid it will also be nullpointerException.
    @Test
    void testLoadFileException() {
        try {
            filename = "input";
            testload = new JobList(companyList, filename);
            j1 = new JobList(companyList, testload.getParsedLines());
            Job j = j1.getJob(0);
            assertEquals(j.getCompany(), "companytest");
            fail("no Exception thrown when supposed to.");
        } catch (IOException e) {

        } catch (NullPointerException e) {

        } catch (InvalidEntryException e) {
            fail("InvalidEntryException thrown when not supposed to.");
        }
    }

    @Test
    void testGetParsedLinesNoException() {
        try {
            testload = new JobList(companyList, filename);
            List toload = testload.getParsedLines();
            JobList j1 = new JobList(companyList, toload);
            Job j = j1.getJob(1);
            assertEquals(j.getJobTitle(), "job1");
            assertEquals(j.getCompany(), "company");
        } catch (InvalidEntryException e) {
            fail("InvalidEntryException thrown when not supposed to.");
        } catch (IOException e) {
            fail("IOException thrown when not supposed to.");
        }

    }

    @Test
    void testSplitOnCommaNoException() {
        try {
            testload = new JobList(companyList, filename);
            List<String> lines = Files.readAllLines(Paths.get(filename));
            List<List>

                    parsedLines = new ArrayList<>();
            String s = "";

            for (String line : lines) {
                ArrayList<String> partsOfLine = testload.splitOnComma(line);
                parsedLines.add(partsOfLine);
                s = "jobTitle: " + partsOfLine.get(2) + " ";
            }
            assertEquals(s, "jobTitle: job1 ");
        } catch (IOException e) {
            fail("IOException thrown when not supposed to.");
        } catch (NullPointerException e) {
            fail("NullPointerException thrown when not supposed to.");
        }
    }
}