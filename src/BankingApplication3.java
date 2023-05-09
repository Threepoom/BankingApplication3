import java.util.Random;
import java.util.Scanner;

public class BankingApplication3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int option = 0;
        int number = 0;
        String name;
        double balance;
        double amount;
        Account account;
        Bank bank = new Bank("GasiGorn");
        while (option != 6){
            System.out.println("Main Menu");
            System.out.println("1.Display All Account");
            System.out.println("2.Open New Account");
            System.out.println("3.Close Existing Account");
            System.out.println("4.Deposit");
            System.out.println("5.Withdraw");
            System.out.println("6.Exit");
            System.out.println();

            System.out.print("Enter your choice: ");
            option = s.nextInt();
            s.nextLine();
            System.out.println();

            switch (option){
                case 1:
                    bank.listAccounts();
                    break;
                case 2:
                    number = generateAccountNumber();
                    System.out.print("Enter Account Name: ");
                    name = s.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    balance = s.nextDouble();
                    account = new Account( number, name, balance);
                    bank.openAccount(account);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    number = s.nextInt();
                    bank.closeAccount(number);
                    System.out.println("Account has been deleted!");
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    number = s.nextInt();
                    account = bank.getAccount(number);
                    System.out.print("Enter Amount: ");
                    amount = s.nextDouble();
                    bank.depositMoney(amount ,account);
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    number = s.nextInt();
                    account = bank.getAccount(number);
                    System.out.print("Enter Amount: ");
                    amount = s.nextDouble();
                    bank.withDrawMoney(account, amount);
                    break;
            }

        }
    }
    public  static int generateAccountNumber(){
        Random random = new Random();
        int accNumber = 100000 + random.nextInt(900000);
        return accNumber;
    }
}