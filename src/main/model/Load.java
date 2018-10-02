//package model;
//
//import Interfaces.Loadable;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Load implements Loadable {
//
//    private List<String> lines;
//    private List<List> parsedLines = new ArrayList<>();
//    private String fileName;
//
//    public Load(String fileName) {
//        this.fileName = fileName;
//    }
//    public void loadFile() throws IOException {
//        lines = Files.readAllLines(Paths.get(fileName));
//        for (String line : lines) {
//            ArrayList<String> partsOfLine = splitOnComma(line);
//            parsedLines.add(partsOfLine);
//            System.out.print("jobTitle: " + partsOfLine.get(0) + " ");
//            System.out.print("company: " + partsOfLine.get(1) + " ");
//            System.out.print("dateApplied: " + partsOfLine.get(2) + " ");
//            System.out.print("jobStatus: " + partsOfLine.get(3) + " ");
//            System.out.println("dateLastChanged: " + partsOfLine.get(4));
//        }
//    }
//
//    public List<List> getParsedLines() {
//        return this.parsedLines;
//    }
//
//
//    public ArrayList<String> splitOnComma(String line){
//        String[] splits = line.split(",");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//}