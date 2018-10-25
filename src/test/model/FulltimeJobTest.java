package model;

import Exceptions.InvalidEntryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FulltimeJobTest {

    private Job j;

    @BeforeEach
    void setUp() throws InvalidEntryException {
        j = new FulltimeJob(1, "testjob", "testcompany");

    }

    // Test Constructor
    @Test
    void TestConstructor() {
        assertEquals(j.getJobTitle(),("testjob"));
        assertFalse(j.getJobTitle().equals("testjob1"));

        assertEquals(j.getCompany(), ("testcompany"));
        assertFalse(j.getJobTitle().equals("testcompany1"));

        // test that date is properly initialised
        // info from https://stackoverflow.com/questions/15925509/java-compare-two-dates
        j.dateApplied = LocalDate.of(2018,9,10);
        LocalDate testdate = LocalDate.of(2018,9,10);
        assertFalse(j.dateApplied.isBefore(testdate));
        assertFalse(j.dateApplied.isAfter(testdate));
    }

    @Test
    // Test get job is properly done.
    void TestgetJobTitle() {
        assertEquals(j.getJobTitle(),("testjob"));
        assertFalse(j.getJobTitle().equals("testjob1"));
    }


    // Test that a valid company is retrieved from the job entry
    @Test
    void TestgettCompany() {
        assertEquals(j.getCompany(), ("testcompany"));
        assertFalse(j.getJobTitle().equals("testcompany1"));
    }

    // Test that a valid date is retrieved
    @Test
    void TestgetDateApplied(){
        j.dateApplied = LocalDate.of(2018,9,10);
        LocalDate testdate = LocalDate.of(2018,9,10);
        assertFalse(j.dateApplied.isBefore(testdate));
        assertFalse(j.dateApplied.isAfter(testdate));
    }

    //Test the JobStatus is retrieved properly
    @Test
    void TestgetJobStatus() {
        j.setJobStatus("teststatus1");
        assertEquals(j.getJobStatus(), "teststatus1");
        assertNotEquals(j.getJobStatus(),"teststatus2");
    }

    // Test that job status is changed to the new one
    @Test
    void TestsetJobStatus() {
        j.setJobStatus("teststatus123");
        assertEquals(j.getJobStatus(), "teststatus123");
        assertFalse(j.getJobStatus().equals("teststatus2"));


    }
}