package bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void testToActivateAccount(){
        account = new Account("sammy",1,"2024");
    }

    @Test
    public void testForAccountDeposit(){
        account.deposit(15000);
        assertEquals(15000, account.checkBalance("2024"));
    }

    @Test
    public void testForAccountWithdraw(){
        account.deposit(10000);
        account.withdraw(7500,"2024");
        assertEquals(2500,account.checkBalance("2024"));
    }

    @Test
    public void testAccountThat_amountGreaterThan_yourBalanceCan_notBeWithdrawn(){
        account.deposit(10000);
        assertThrows(InsufficientFundsException.class, ()-> account.withdraw(30000,"2024"));
    }

    @Test
    public void testAccountFor_NegativeAmountCan_notBeWithdrawn() {
        assertThrows(InvalidAmountException.class, ()-> account.withdraw(-5000,"2024"));
    }

    @Test
    public void testAccountThat_balanceCanBeChecked_andConfirmed(){
        account.deposit(50000);
        assertEquals(50000,account.checkBalance("2024"));
    }

    @Test
    public void testAccountForWrongPin(){
        account.deposit(50000);
        assertThrows(InvalidPinException.class,()-> account.checkBalance("000033"));
        assertThrows(InvalidPinException.class,()-> account.checkBalance("202425"));
    }

    @Test
    public void testThat_active_AccountNumber_isCorrect(){
        account.deposit(50000);
        assertEquals(1,account.getAccountNumber());
    }

}
