package bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankTest {

    private Bank bank;
    private Account account;

    @BeforeEach
    public void initailizeBank(){
        bank = new Bank("UBA GROUP");
    }

    @Test
    public void registerCustomerTest(){
        Account account = bank.registerCustomer("Samson","Ibironke","2025");
        assertEquals(1,bank.getAccounts());
    }

    @Test
    public void testThatMore_customerAccount_canBeRegistered_successfully(){
        bank.registerCustomer("Samson","Ibironke","2025");
        bank.registerCustomer("Olamidipupo","En-hakkore","6899");
        bank.registerCustomer("Kent","Joshua","2025");
        bank.registerCustomer("Curtlyn","Berkly","1985");
        bank.registerCustomer("David","Black","1960");
        assertEquals(5,bank.getAccounts());
    }

    @Test
    public void testForAccountTo_doDepositTransaction_successfully(){
        Account account= bank.registerCustomer("Samson","Ibironke","2025");
        bank.deposit(1,17000);
        assertEquals(17000,bank.checkBalance(1,"2025"));
    }

    @Test
    public void testThatMore_thanOneAccount_canDoDepositTransaction_successfully(){
        Account account= bank.registerCustomer("samson","Ibironke","2025");
        bank.deposit(1,15000);
        assertEquals(15000,bank.checkBalance(1,"2025"));
        Account account1= bank.registerCustomer("Joel","En-hakkore","1234");
        bank.deposit(2,12000);
        assertEquals(12000, bank.checkBalance(2,"1234"));
    }

    @Test
    public void testAccount_toWithdrawSuccessfully(){
        bank.registerCustomer("Samson","Ibironke","2024");
        bank.deposit(1,20000);
        assertEquals(20000, bank.checkBalance(1, "2024"));
        bank.withdraw(1,15000,"2024");
        assertEquals(5000,bank.checkBalance(1,"2024"));
    }

    @Test
    public void testAccount_ForTransferTo_anotherSuccessfully(){
        bank.registerCustomer("Samson","Ibironke","2024");
        bank.deposit(1,50000);
        bank.registerCustomer("Joshua","Olamidipupo","1960");
        bank.transfer(1,2,30000,"2024");
        assertEquals(20000,bank.checkBalance(1,"2024"));
    }

    @Test
    public void testToSearch_andFindAccount(){
        Account account = bank.registerCustomer("Babatunde","Olamide","2025");
        assertEquals(account, bank.searchAccount(account.getAccountNumber()));
    }

    @Test
    public void testBankThrowsExceptionForInvalidAccountNumber(){
        assertThrows(InvalidAccountException.class, () -> bank.searchAccount(1));
    }

    @Test
    public void testToFind_andRemoveRegistered_AccountNumber(){
        Account account1 = bank.registerCustomer("johnson","Micheal","1990");
        Account account2 = bank.registerCustomer("Olamide","Ayomide","2025");
        assertEquals(2,bank.getAccounts());
        bank.removeAccount(1,"1990");
        assertEquals(1,bank.getAccounts());
    }

    @Test
    public void testThatAccountNumber_canNotBeRemovedWhen_passwordIsWrong(){
        Account account1 = bank.registerCustomer("Samson","Babatunde","1900");
        assertThrows(InvalidPinException.class,() -> bank.removeAccount(1,"1920"));
    }

}
