package bank.bankApp;

import bank.Account;
import bank.Bank;

import java.util.Scanner;
import java.util.InputMismatchException;

public class BankApp {

    public static class MyBank {
        public static void main(String[] args) {
            System.out.println("\n Welcome to Samibyrone Group Bank, We offer the best service. ");
            mainDeal();
        }

        static Scanner response = new Scanner(System.in);
        static Scanner response2 = new Scanner(System.in);
        static Bank bank = new Bank();

        public static void mainDeal() {

            while (true) {
                Scanner input = new Scanner(System.in);
                System.out.println();
                System.out.println("""
                     =======================================
                     Welcome to Samibyrone-Group bank
                     =======================================
                    
                     enter 1 For Opening a new Account
                     enter 2 to Deposit
                     enter 3 to Withdraw
                     enter 4 to Check balance
                     enter 5 to Transfer
                     enter 6 to Find account number
                     enter 7 to Remove account number
                     enter 8 to Exit
                    """);

                int response = 0;

                try {
                    response = input.nextInt();

                }catch (InputMismatchException e) {
                    throw new InputMismatchException("your selected option is invalid!!!!  \uD83D\uDE0F\uD83D\uDE0F");
                }


                switch (response) {
                    case 1:
                        newRegister();
                    case 2:
                        newDeposit();
                    case 3:
                        newWithdraw();
                    case 4:
                        newCheckBalance();
                    case 5:
                        newTransfer();
                    case 6:
                        newFindAccount();
                    case 7:
                        newRemoveAccount();
                    case 8:
                        break;
                }
            }
        }

        public static void newRegister() {
            System.out.println("Enter your first name");
            String firstName = response.next();
            System.out.println("Enter your second name");
            String surName = response.next();
            System.out.println("Enter your pin number");
            String pin = response.next();

            try {
                Account createdAccount = bank.registerCustomer(firstName, surName, pin);
                System.out.println("\n Your account has been created successfully... \n" + "\n Your account name is: " +createdAccount.getName()+
                        "\n Your account number is: " + " " +createdAccount.getAccountNumber()+ " " + "\n Your account pin is: " + " " +createdAccount.getPin());
                }catch (Exception e){
                    System.out.println("Error occurred while creating account kindly input you details again with the right pin");
                }

            mainDeal();
        }

        public static void newDeposit() {
            System.out.println("Enter the account number you want deposit into");
            int accountNumber = response.nextInt();

            System.out.println("Enter your deposit amount");
            int amount = response.nextInt();

            bank.deposit(accountNumber, amount);
            System.out.println("\n Your deposit is successful. \n" + " You made a deposit of " +amount+ " into this account number " +accountNumber);

            mainDeal();
        }

        public static void newWithdraw() {
            System.out.println("Enter the account number you want to withdraw");
            int accountNumber = response.nextInt();

            System.out.println("Enter your withdraw amount");
            int amount = response.nextInt();

            System.out.println("Kindly enter your pin");
            String pinNumber = response.next();

            try {
                bank.withdraw(accountNumber, amount, pinNumber);
                System.out.println("\n Your withdraw was successful \n" + "\n You made a withdrawal of " +amount+ " from this account number " +accountNumber);
            } catch (Exception e) {
                System.out.println(" ");
                mainDeal();
            }

            mainDeal();
        }

        public static void newFindAccount(){
            System.out.println("Enter the account number to want to find");
            int accountNumber = response.nextInt();

            try {
                bank.searchAccount(accountNumber);
                System.out.println("\n Account was found successfully \n" + "\n Your account number is: " + " " + accountNumber);
            }catch (Exception e) {
                System.out.println("Account was not found");
            }
            mainDeal();
        }

        public static int newCheckBalance(){
            System.out.println("Enter the account you want to check balance");
            int accountNumber = response.nextInt();

            System.out.println("Kindly enter your pin");
            String pin = response.next();

            try {
                bank.checkBalance(accountNumber,pin);
                System.out.println("\n The balance was successfully checked \n" + "\n Your account number is: " + " " + accountNumber);
            }catch (Exception e){
                System.out.println("are you sure you have the right details?");
            }
            mainDeal();
           return newCheckBalance();
        }

        public static void newTransfer() {
            System.out.println("Enter sender's account number");
            int senderAccount = response.nextInt();

            System.out.println("Enter receiver's account number");
            int receiverAccount = response.nextInt();

            System.out.println("Enter amount you want to send");
            int amount = response.nextInt();

            System.out.println("Kindly enter your pin");
            String pin = response.next();

            try {
                bank.transfer(senderAccount, receiverAccount, amount, pin);
                System.out.println("Your transfer was successfully \n" + "\n Your Transfer " + amount + " to " + receiverAccount + "account Number.");
            } catch (Exception e) {
                System.out.println("Your transaction failed!!!!!");
            }
            mainDeal();
        }

        public static void newRemoveAccount(){
            System.out.println("enter the account number you want to delete");
            int accountNumber = response.nextInt();

            System.out.println("Kindly enter your pin");
            String pin = response.next();

            try {
                bank.removeAccount(accountNumber, pin);
                System.out.println("Your account was successfully deleted");
            }catch (Exception e) {
                System.out.println("there is no available account to be deleted");
            }

        }

    }
}
