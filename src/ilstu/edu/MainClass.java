package ilstu.edu;
import java.util.*;
public class MainClass {

    public static void main(String[] args) {

        String filename = null;
        String studentname;
        Boolean hasFileName = false;
        Boolean keepGoing = true;

        while (keepGoing) {
            System.out.println();
            System.out.println("Select an option by entering its number");

            if (hasFileName) {
                System.out.println("Current file name: " + filename);
            }

            System.out.println(
                    "[1] Enter file name" +
                            " [2] Print list of all students" +
                            " [3] Generate report card" +
                            " [4] Display statistics" +
                            " [5] Exit");

            Scanner userOptionSelected = new Scanner(System.in);
            int optionSelected = userOptionSelected.nextInt();

            if (optionSelected == 1) {
                StudentReport a = new StudentReport(filename);
                Scanner userInputFilename = new Scanner(System.in);
                System.out.println("Enter file name:");
                filename = userInputFilename.nextLine() + ".csv";
                hasFileName = true;
                a.readFile(filename);

            } else if (optionSelected == 2) {

                if(filename != null) {

                    StudentReport a = new StudentReport(filename);
                    a.readFile(filename);
                    System.out.println();

                    for (int i = 0; i < a.students.length; i++) {
                        if (a.students[i] != null) {
                            System.out.println(a.students[i]);
                        }
                    }

                } else {
                    System.out.println("Please enter file name to continue");
                }
            } else if (optionSelected == 3) {
                if(filename != null) {
                    Scanner userNameInput = new Scanner(System.in);
                    System.out.println("Enter student name:");
                    studentname = userNameInput.nextLine();
                    StudentReport a = new StudentReport(filename);
                    a.readFile(filename);
                    a.writeFile(studentname);
                } else {
                    System.out.println("Please enter file name to continue");
                }
            } else if (optionSelected == 4) {
                if(filename != null) {
                    StudentReport a = new StudentReport(filename);
                    a.readFile(filename);
                    a.printStatistics();
                } else {
                    System.out.println("Please enter a file name to continue");
                }

            } else if (optionSelected == 5) {
                keepGoing = false;
            } else {
                System.out.println("You have entered an incorrect option. Please try again");
            }
        }
    }

}