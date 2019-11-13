package ui.atm;

import account.CheckingAccount;
import account.CsvAccountRepository;
import account.AccountRepository;

import java.util.Scanner;

public class ConsoleAtmUi {

    public static void main(String[] args) {
        AccountRepository repo = new CsvAccountRepository();
        CheckingAccount account = new CheckingAccount(repo.readAll().Value().get());

        String newLine = System.getProperty("line.separator");
        Scanner in = new Scanner(System.in);
        String selection;

        System.out.println("Welcome to ATM" + newLine);
        System.out.println("Please enter your account number" + newLine);
        String accountNumber = in.nextLine();
        do {
            System.out.println("Please Select an option:" + newLine + newLine +
                                "   [B] Balance" + newLine +
                                "   [D] Deposit" + newLine +
                                "   [W] Withdrawal" + newLine +
                                "   [H] Transaction History" + newLine +
                                "   [Q] Quit" + newLine);
            selection = in.nextLine();

            switch (selection) {
                case "B":
                    System.out.println("Your account balance is: " + account.getBalance() + newLine);
                    break;
                case "D":
                    System.out.println("Enter deposit amount: ");
                    String deposit = in.nextLine();
                    account.deposit(Double.parseDouble(deposit));
                    String newLine11 = System.getProperty("line.separator");
                    System.out.println("Your account balance is: " + account.getBalance() + newLine);
                    break;
                case "W":
                    System.out.println("Enter withdrawal amount: ");
                    String withdrawal = in.nextLine();
                    account.withdraw(Double.parseDouble(withdrawal));
                    String newLine21 = System.getProperty("line.separator");
                    System.out.println("Your account balance is: " + account.getBalance() + newLine21);
                    break;
            }
        } while (!selection.equals("Q"));
    }

}
