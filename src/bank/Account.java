package bank;

import java.util.Objects;

public class Account {
        private String name;
        private int number;
        private String passCode;
        private int balance;


        public String getName() {
            return name;
        }

        public String getPin() {
            return this.passCode;
        }

        public Account(){
        }

        public int getAccountNumber(){
            return number;
        }

        public Account(String name, int number, String pinNumber) {
            this.name = name;
            this.number = number;
            this.passCode = pinNumber;
            if(pinNumber.length() > 4) {
                throw new InvalidPinException("Your pin can not be validated");
            }
        }

        public void deposit(int amount) {
            if(amount < 0)throw new InvalidAmountException("We don't do that here, it's better you know that you Can not deposit a negative amount!!!!");
            balance += amount;
        }

        public int checkBalance(String pinNumber){
            if(Objects.equals(this.passCode, pinNumber)) {
                return balance;
            }
            throw  new InvalidPinException("Your information provided can not be validated on our system, so therefore you can not check your balance");
        }


        public void withdraw(int amount,String pinNumber) {
            if (amount > balance) throw new InsufficientFundsException("you don't have enough money for this transaction!!!!!");
            if(!this.passCode.equals(pinNumber)) throw new InvalidPinException("Invalid pin number");
            if(amount < 0) throw new InvalidAmountException("We don't do that here, it's better you know that you Can not withdraw a negative amount");
            balance -= amount;
        }

}
