import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of subjects: ");
        int subjects = scanner.nextInt();
        
        int totalMarks = 0;
        for(int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks for subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();
            totalMarks += marks;
        }
        
        double averagePercentage = (double) totalMarks / subjects;
        String grade;
        
        if(averagePercentage >= 90) grade = "A+";
        else if(averagePercentage >= 80) grade = "A";
        else if(averagePercentage >= 70) grade = "B";
        else if(averagePercentage >= 60) grade = "C";
        else if(averagePercentage >= 50) grade = "D";
        else grade = "F";
        
        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }
}