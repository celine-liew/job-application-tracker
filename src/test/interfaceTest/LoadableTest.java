package interfaceTest;

import Exceptions.InvalidEntryException;
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
    Loadable load;
    JobList j1;

    @BeforeEach
    void setUp() throws Exception {
        filename = "testFile.csv";
        //load = new JobList(filename);

    }

    @Test
    void testLoadFilenoException() {
        try {
            load = new JobList(filename);
            j1 = new JobList(load.getParsedLines());
            Job j = j1.getJob(0);
            assertEquals(j.getCompany(), "companytest");
        } catch (NullPointerException e) {
            fail("NullPointerException thrown when not supposed to.");
        } catch (InvalidEntryException e) {
            fail("InvalidEntryException thrown when not supposed to.");
        } catch (IOException e) {
            fail("IOException thrown when not supposed to.");
        }
    }

    // When filename is invalid it will also be nullpointerException.
    @Test
    void testLoadFileException() {
        try {
            filename = "input";
            load = new JobList(filename);
            j1 = new JobList(load.getParsedLines());
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
            load = new JobList(filename);
            List toload = load.getParsedLines();
            JobList j1 = new JobList(toload);
            Job j = j1.getJob(0);
            assertEquals(j.getJobTitle(), "intern");
            assertEquals(j.getCompany(), "companytest");
        } catch (InvalidEntryException e) {
            fail("InvalidEntryException thrown when not supposed to.");
        } catch (IOException e) {
            fail("IOException thrown when not supposed to.");
        }

    }

    @Test
    void testSplitOnCommaNoException() {
        try {
            load = new JobList(filename);
            List<String> lines = Files.readAllLines(Paths.get(filename));
            List<List>

                    parsedLines = new ArrayList<>();
            String s = "";

            for (String line : lines) {
                ArrayList<String> partsOfLine = load.splitOnComma(line);
                parsedLines.add(partsOfLine);
                s = "jobTitle: " + partsOfLine.get(1) + " ";
            }
            assertEquals(s, "jobTitle: intern ");
        } catch (IOException e) {
            fail("IOException thrown when not supposed to.");
        } catch (NullPointerException e) {
            fail("NullPointerException thrown when not supposed to.");
        }
    }
}