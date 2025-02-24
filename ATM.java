import java.util.Scanner;

class BankAccount {
    double balance;
    
    BankAccount(double b) {
        balance = b;
    }
}

public class ATM {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.println("\n1. Balance\n2. Deposit\n3. Withdraw\n4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            
            if(choice == 1) {
                System.out.println("Balance: $" + account.balance);
            }
            else if(choice == 2) {
                System.out.print("Deposit amount: $");
                double amt = sc.nextDouble();
                if(amt > 0) account.balance += amt;
            }
            else if(choice == 3) {
                System.out.print("Withdraw amount: $");
                double amt = sc.nextDouble();
                if(amt > 0 && amt <= account.balance) account.balance -= amt;
            }
            else if(choice == 4) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }
}