package ilstu.edu;

import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class StudentReport {

    public String filename;
    private double[][] grades;
    public String[] students;
    private String[] gradedItems;
    private int numberOfStudents;

    public StudentReport(String filename) {
        this.filename = filename;
        this.gradedItems = new String[8];
        this.students = new String[100];
        this.grades = new double[100][8];
        this.numberOfStudents = 0;
    }

    public void readFile(String fileName) {
        try {
            Scanner fileReader = new Scanner(new File(fileName));
            List<String> lines = new ArrayList<>();

            while (fileReader.hasNextLine()) {
                lines.add(fileReader.nextLine());
            }

            if (!lines.isEmpty()) {
                String[] header = lines.get(0).split(",");
                for (int i = 1; i < header.length; i++) {
                    gradedItems[i - 1] = header[i];
                }

                int studentCount = 0;
                for (int i = 1; i < lines.size(); i++) {
                    String[] studentInfo = lines.get(i).split(",");
                    students[studentCount] = studentInfo[0];

                    for (int j = 1; j < studentInfo.length; j++) {
                        grades[studentCount][j - 1] = Double.parseDouble(studentInfo[j]);
                    }

                    studentCount++;
                }
                this.numberOfStudents = studentCount;
            } else {
                System.out.println("File is empty.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String studentName) {
        int studentIndex = -1;
        for (int i = 0; i < numberOfStudents; i++) {
            if (students[i].equals(studentName)) {
                studentIndex = i;
                break;
            }
        }

        if (studentIndex != -1) {
            try {
                String fileName = students[studentIndex] + ".txt";
                FileWriter writer = new FileWriter(fileName);

                writer.write("Student Name: " + students[studentIndex] + "\n\n");

                for (int i = 0; i < gradedItems.length; i++) {
                    String itemName = gradedItems[i];
                    double grade = grades[studentIndex][i];
                    writer.write(itemName + ": " + grade + "\n");
                }

                double totalGrade = 0.0;
                for (int i = 0; i < gradedItems.length; i++) {
                    totalGrade += grades[studentIndex][i];
                }
                writer.write("\nTotal: " + totalGrade + "\n");

                char letterGrade;
                if (totalGrade >= 90) {
                    letterGrade = 'A';
                } else if (totalGrade >= 80) {
                    letterGrade = 'B';
                } else if (totalGrade >= 70) {
                    letterGrade = 'C';
                } else if (totalGrade >= 60) {
                    letterGrade = 'D';
                } else {
                    letterGrade = 'F';
                }
                writer.write("Letter Grade: " + letterGrade + "\n");

                writer.close();

                System.out.println("Report card for " + students[studentIndex] + " has been generated and saved as " + fileName);
            } catch (IOException e) {
                System.out.println("An error occurred while writing the report card file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void printStatistics() {
        double classAverage = 0.0;
        double lowestTotal = Double.MAX_VALUE;
        double highestTotal = Double.MIN_VALUE;

        for (int i = 0; i < numberOfStudents; i++) {
            double studentTotal = 0.0;

            for (int j = 0; j < gradedItems.length; j++) {
                studentTotal += grades[i][j];
            }

            if (studentTotal < lowestTotal) {
                lowestTotal = studentTotal;
            }

            if (studentTotal > highestTotal) {
                highestTotal = studentTotal;
            }

            classAverage += studentTotal;
        }

        if (numberOfStudents > 0) {
            classAverage /= numberOfStudents;
        }

        System.out.println("Class Statistics:");
        System.out.println("Class Average: " + String.format("%.2f", classAverage));
        System.out.println("Lowest Total Score: " + lowestTotal);
        System.out.println("Highest Total Score: " + highestTotal);
    }

}
