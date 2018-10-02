package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobListTest {

    private JobList jobs;
    private List<String> lines;
    private List<List> parsedLines = new ArrayList<>();
    private String fileName;

    @BeforeEach
    void setUp() {
        jobs = new JobList();
    }

    // Test that job is added to list correctly
    @Test
    void TestaddJob() {
        jobs.addJob("testTitle","testCompany");
        Job j = jobs.getJob(0);
        assertTrue(jobs.getJobList().contains(j));
        assertEquals(j.getJobTitle(),"testTitle");
        assertEquals(j.getCompany(),"testCompany");
    }

    // Test that the job list returns true if empty
    @Test
    void TestjobLisEmpty() {
        assertTrue(jobs.jobLisEmpty());
    }

    // Test that the job list returns false if empty
    @Test
    void TestjobLisNotEmpty() {
        jobs.addJob("testTitle","testCompany");
        Job j = jobs.getJob(0);
        assertFalse(jobs.jobLisEmpty());
    }

    // Test that out of bound index returns false, and in-range index returns true
    @Test
    void TesttvalidJoblistRange() {
        jobs.addJob("testTitle","testCompany");
        jobs.addJob("testTitle2","testCompany2");
        assertTrue(jobs.invalidJoblistRange(2));
        assertFalse(jobs.invalidJoblistRange(1));
        assertFalse(jobs.invalidJoblistRange(0));
    }

    // Test that job retrieved in index is correct
    @Test
    void TestgetJob() {
        jobs.addJob("testTitle","testCompany");
        jobs.addJob("testTitle2","testCompany2");
        assertTrue(jobs.getJob(0 ).getCompany().equals("testCompany"));
        assertTrue(jobs.getJob(1 ).getCompany().equals("testCompany2"));
        assertFalse(jobs.getJob(0 ).getCompany().equals("testCompany2"));
        assertFalse(jobs.getJob(1 ).getCompany().equals("testCompany"));
    }


    // Test that job list is retrieved correctly.
    @Test
    void getJobList() {
        jobs.addJob("testTitle","testCompany");
        Job j = jobs.getJob(0);
        assertTrue(jobs.getJobList().contains(j));
    }
}