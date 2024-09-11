package bank;

import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts = new ArrayList<>();
    private int accountNumber;
    private String name;


    public Bank(String name) {
        this.name = name;
    }

    public Bank() {

    }

    public Account registerCustomer(String firstName, String lastName, String myPin) {
        String fullName = firstName + " " + lastName;
        Account account = new Account(fullName, accounts.size() + 1, myPin);
        accounts.add(account);
        this.accountNumber = accountNumber;
        return account;
    }

    public int getAccounts() {
        return accounts.size();
    }

    public void deposit(int accountNumber, int amount) {
        Account myAccount = new Account();
        for (int index = 0; index < accounts.size(); index++) {
            if (accounts.get(index).getAccountNumber() == accountNumber) {
                myAccount = accounts.get(index);
            }
        }

        if (myAccount == null) throw new InvalidAccountException("Invalid number for this Account: " + accountNumber);
        myAccount.deposit(amount);
        if (amount < 0) {
            throw new InvalidAmountException("Be very careful, You Can not deposit a negative amount");
        }

    }

    public int checkBalance(int accountNumber, String myPin) {
        Account myAccount = searchAccount(accountNumber);
        return myAccount.checkBalance(myPin);
    }

    public void withdraw(int accountNumber, int amount, String myPin) {
        Account myAccount = searchAccount(accountNumber);
        myAccount.withdraw(amount, myPin);
        if (amount < 0) {
            throw new InvalidAmountException("We don't do that here, it's better you know that you Can not withdraw a negative amount");
        }

    }

    public void transfer(int senderAccountNumber, int receiverAccountNumber, int amount, String senderPin) {
        Account senderAccount = searchAccount(senderAccountNumber);
        Account receiverAccount = searchAccount(receiverAccountNumber);
        senderAccount.withdraw(amount, senderPin);
        receiverAccount.deposit(amount);
    }


    public Account searchAccount(int accountNumber) {
        Account foundAccount = new Account();
        for (int index = 0; index < accounts.size(); index++) {
            if (accounts.get(index).getAccountNumber() == accountNumber) {
                foundAccount = accounts.get(index);
                return foundAccount;
            }
        }
        throw new InvalidAccountException("You got it wrong this time, Account with this exact account number does not exist here, please do better next time!!!");

    }

    public void removeAccount(int accountNumber, String pin) {
        Account foundAccount = searchAccount(accountNumber);
        if (foundAccount.getPin().equalsIgnoreCase(pin)) {
            accounts.remove(foundAccount);
        } else {
            throw new InvalidPinException("you password is wrong!!!, Are you sure own this account?");
        }

    }
}