package interfaceTest;

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

    @BeforeEach
    void setUp() throws Exception {
        filename = "testFile.csv";
        load = new JobList(filename);

    }
    @Test
    void testLoadFile() throws IOException {
        load.loadFile();
        JobList j1 = new JobList(load.getParsedLines());
        Job j = j1.getJob(0);
        assertEquals(j.getCompany(),"companytest");
    }

    @Test
    void testGetParsedLines() throws IOException {
        load.loadFile();
        List toload = load.getParsedLines();
        JobList j1 = new JobList(toload);
        Job j = j1.getJob(0);
        assertEquals(j.getJobTitle(), "jobtitletest");
        assertEquals(j.getCompany(),"companytest");
    }

    @Test
    void testSplitOnComma()  throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        List<List>


                parsedLines = new ArrayList<>();
        String s = "";

        for (String line : lines) {
            ArrayList<String> partsOfLine = load.splitOnComma(line);
            parsedLines.add(partsOfLine);
            s = "jobTitle: " + partsOfLine.get(0) + " ";
        }
        assertEquals(s, "jobTitle: jobtitletest ");
    }
}