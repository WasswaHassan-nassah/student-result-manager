package resultmanager;
import java.util.Scanner;
public class StudentResultMananger {
   static Scanner scanner = new Scanner(System.in);
   //Simple arrays act as storage for this small app
   static String[] names = new String[100];
   static int[] scores = new int[100];
   static int studentCount =0;
		   
	public static void main(String[] args) {
		int choice;
		//DO-While Loop: menu must be shown at least once,then repeats
		//until the user chooses to exist.
		do {
            printMenu();
            choice = readMenuChoice();

            switch (choice) {
                case 1:
                    addStudents();
                    break;
                case 2:
                    viewResults();
                    break;
                case 3:
                    System.out.println("Goodbye! Results saved for this session.");
                    break;
                default:
                    System.out.println("Invalid option, please choose 1-3.");
            }

        } while (choice != 3);

        scanner.close();
    }

    static void printMenu() {
        System.out.println(" STUDENT RESULT MANAGER ");
        System.out.println("1. Add students and scores");
        System.out.println("2. View results and class summary");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    static int readMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // triggers "invalid option" in the switch
        }
    }

    static void addStudents() {
        System.out.print("How many students do you want to add? ");
        int count = Integer.parseInt(scanner.nextLine().trim());

        // FOR LOOP: iterate a known number of times, once per student
        for (int i = 0; i < count && studentCount < names.length; i++) {
            System.out.print("Enter name for student " + (studentCount + 1) + ": ");
            String name = scanner.nextLine().trim();

            int score = -1;

            // WHILE LOOP: keep asking until the score entered is valid (0-100)
            while (score < 0 || score > 100) {
                System.out.print("Enter score for " + name + " (0-100): ");
                try {
                    score = Integer.parseInt(scanner.nextLine().trim());
                    if (score < 0 || score > 100) {
                        System.out.println("Score must be between 0 and 100. Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("That's not a number. Try again.");
                    score = -1;
                }
            }

            names[studentCount] = name;
            scores[studentCount] = score;
            studentCount++;
        }

        System.out.println(count + " student(s) added.");
    }

    static void viewResults() {
        if (studentCount == 0) {
            System.out.println("No students added yet. Choose option 1 first.");
            return;
        }

        System.out.println(" RESULTS ");
        int totalScore = 0;
        int passCount = 0;

        // FOR LOOP: go through every stored student and print their result
        for (int i = 0; i < studentCount; i++) {
            int score = scores[i];

            // TERNARY: pass/fail status
            String status = (score >= 50) ? "PASS" : "FAIL";

            // TERNARY (chained): letter grade
            String grade = (score >= 80) ? "A"
                          : (score >= 70) ? "B"
                          : (score >= 60) ? "C"
                          : (score >= 50) ? "D"
                          : "F";

            System.out.println(names[i] + " - Score: " + score
                    + " - Grade: " + grade + " - " + status);

            totalScore += score;
            passCount += (status.equals("PASS")) ? 1 : 0; // ternary again
        }

        double average = (double) totalScore / studentCount;

        System.out.println(" CLASS SUMMARY ");
        System.out.println("Number of students : " + studentCount);
        System.out.println("Class average       : " + String.format("%.2f", average));
        System.out.println("Passed               : " + passCount);
        System.out.println("Failed               : " + (studentCount - passCount));

        // TERNARY: overall class performance remark
        String remark = (average >= 50) ? "Class performance is satisfactory."
                                         : "Class performance needs improvement.";
        System.out.println(remark);
    }


	}


