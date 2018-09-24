package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    private Job j;

    @BeforeEach
    void setUp() {
        j = new Job("testjob", "testcompany");
    }

    // Test that a valid job title is retrieved
    @Test
    void TestgetJobTitle() {
        assertTrue(j.getJobTitle().equals("testjob"));
        assertFalse(j.getJobTitle().equals("testjob1"));
    }

    // Test that a valid company is retrieved
    @Test
    void TestsetCompany() {
        assertTrue(j.getJobTitle().equals("testcompany"));
        assertFalse(j.getJobTitle().equals("testcompany1"));
    }

    // Test that a valid date is retrieved
    @Test
    void TestgetDateApplied() {
        assertTrue(j.getDateApplied().equals("today"));
        assertFalse(j.getDateApplied().equals("yesterday"));
    }

    @Test
    void getJobStatus() {
    }

    // Test that job status is changed if the new job status is not the same as current.
    @Test
    void setJobStatus() {
    }
}