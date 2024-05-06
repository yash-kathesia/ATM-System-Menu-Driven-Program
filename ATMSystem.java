
/*
 * @Author YASH KATHESIA
 * Objective: Menu Driven program of ATM Machine 
*/
import java.util.*;

class TransactionHistory {
    private StringBuilder transactionHistory = new StringBuilder();

    public void addTransaction(String transaction) {
        transactionHistory.append(transaction).append("\n");
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History :");
        System.out.println(transactionHistory);
    }
}

class Withdraw {
    public static void withdraw(TransactionHistory transactionHistory) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        if (amount > ATMSystem.balance) {
            System.out.println("Insufficient funds!");
        } else {
            ATMSystem.balance -= amount;
            transactionHistory.addTransaction("Withdrawal: -" + amount);
            System.out.println("Withdrawal successful. Remaining balance: " + ATMSystem.balance);
        }
    }
}

class Deposit {
    public static void deposit(TransactionHistory transactionHistory) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        ATMSystem.balance += amount;
        transactionHistory.addTransaction("Deposit: +" + amount);
        System.out.println("Deposit successful. New balance: " + ATMSystem.balance);
    }
}

class Transfer {
    public static void transfer(TransactionHistory transactionHistory) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the recipient's User ID : ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter the amount to transfer : ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        if (amount > ATMSystem.balance) {
            System.out.println("Insufficient funds!!!");
        } else {
            ATMSystem.balance -= amount;
            transactionHistory.addTransaction("Transfer to " + recipientId + " : " + amount);
            System.out.println("Transfer successful. Remaining balance: " + ATMSystem.balance);
        }
    }
}

public class ATMSystem {
    private static Scanner scanner = new Scanner(System.in);
    // USER ID
    private static String userId = "21028";
    // USER PIN
    private static String userPin = "38009 ";
    // Initial Balance is 1000000Rs.
    public static double balance = 1000000.00;
    private static TransactionHistory transactionHistory = new TransactionHistory();

    public static void main(String[] args) {
        login();
        boolean quit = false; // Flag to control the loop
        while (!quit) {
            displayMenu();
            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            }
            switch (choice) {
                case 1:
                    transactionHistory.viewTransactionHistory();
                    break;
                case 2:
                    Withdraw.withdraw(transactionHistory);
                    break;
                case 3:
                    Deposit.deposit(transactionHistory);
                    break;
                case 4:
                    Transfer.transfer(transactionHistory);
                    break;
                case 5:
                    System.out.println("Balance : " + balance);
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM system.\nHave a nice day!!!");
                    // Set quit to true to exit the loop
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.\nPlease try again later.");
            }
        }
        // Close the scanner before exiting the program
        scanner.close();
    }

    private static void login() {
        System.out.println("Welcome to the ATM system!");
        System.out.print("Enter your User ID : ");
        String inputId = scanner.nextLine();
        System.out.print("Enter your PIN : ");
        String inputPin = scanner.nextLine();

        if (!inputId.equals(userId) || !inputPin.equals(userPin)) {
            System.out.println("Invalid user ID or PIN. Exiting...");
            System.exit(0);
        } else {
            System.out.println("Login Successful!!!");
        }
    }

    private static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. View Balance");
        System.out.println("6. Quit");
        System.out.print("Enter your choice : ");
    }
}
