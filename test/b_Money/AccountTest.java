package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
		
		testAccount.addTimedPayment("1", 1, 1, new Money(100000, SEK), SweBank, "Alice");
	}
	
	@Test
	public void testAddTimedPayment() {
		testAccount.addTimedPayment("2", 1, 1, new Money(100000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("2"));
	}
	
	@Test
	public void testRemoveTimedPayment() {
		testAccount.removeTimedPayment("1");
		assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		Integer fromBefore = testAccount.getBalance().getAmount();
		Integer toBefore = SweBank.getBalance("Alice");
		testAccount.addTimedPayment("3", 1, 0, new Money(10000, SEK), SweBank, "Alice");
		
		testAccount.tick();
		
		assertEquals(Integer.valueOf(fromBefore - 10000), testAccount.getBalance().getAmount()); // 9890000 instead of 9990000
		assertEquals(Integer.valueOf(toBefore + 10000), SweBank.getBalance("Alice"));
	}

	@Test
	public void testDeposit() {
		Integer before = testAccount.getBalance().getAmount();
		testAccount.deposit(new Money(50000, SEK));
		assertEquals(Integer.valueOf(before + 50000), Integer.valueOf(testAccount.getBalance().getAmount()));
	}
	
	@Test
	public void testWithdraw() {
		Integer before = testAccount.getBalance().getAmount();
		testAccount.withdraw(new Money(10000, SEK));
		assertEquals(Integer.valueOf(before - 10000), Integer.valueOf(testAccount.getBalance().getAmount()));
		
	}
	@Test
	public void testGetBalance() {
		Account testAccount2 = new Account("Bob", SEK);
		testAccount2.deposit(new Money(15000, SEK));
		assertEquals(Integer.valueOf(15000), Integer.valueOf(testAccount2.getBalance().getAmount()));
	}
}
