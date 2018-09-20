package ui;

public class Main {

    public static void applyJob() {
        System.out.println("Job applied! Good Luck!");
    }

    public static void statusUpdate() {
        System.out.println("Job Application Status Updated ");
    }

    public static void main(String[] args) {
        System.out.println("Apply Job or update job status?");
        applyJob();
        statusUpdate();
    }
}
