package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		DanskeBank.openAccount("Antony");
		assertEquals(Integer.valueOf(0), DanskeBank.getBalance("Antony")); // AccountDoesNotExistException
		assertThrows(AccountExistsException.class, () -> DanskeBank.openAccount("Antony"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(10000, SEK)); // AccountDoesNotExistException
		assertEquals(Integer.valueOf(10000), SweBank.getBalance("Bob"));
		assertThrows(AccountDoesNotExistException.class, () -> {SweBank.deposit("Mary", new Money(500, SEK));});
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(10000, SEK)); // AccountDoesNotExistException
		Nordea.withdraw("Bob", new Money(5000, SEK));
		assertEquals(Integer.valueOf(5000), Nordea.getBalance("Bob")); // 15000 instead of 5000
		assertThrows(AccountDoesNotExistException.class, () -> {SweBank.withdraw("Mary", new Money(100, SEK));});
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(0), DanskeBank.getBalance("Gertrud")); // AccountDoesNotExistException
		DanskeBank.deposit("Gertrud", new Money(100, DKK));
		assertEquals(Integer.valueOf(100), DanskeBank.getBalance("Gertrud"));
		assertThrows(AccountDoesNotExistException.class, () -> {SweBank.getBalance("Mary");});
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(10000, SEK)); // NullPointerException
		Integer fromBefore = SweBank.getBalance("Ulrika");
		Integer toBefore = SweBank.getBalance("Bob");
		SweBank.transfer("Ulrika", "Bob", new Money(5000, SEK));
		assertEquals(Integer.valueOf(fromBefore - 5000), SweBank.getBalance("Ulrika")); // 10000 instead of 5000
		assertEquals(Integer.valueOf(toBefore + 5000), SweBank.getBalance("Bob"));
		assertThrows(AccountDoesNotExistException.class, () -> {SweBank.transfer("Sam", "Harry", new Money(2500, SEK));});
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(10000, SEK)); // NullPointerException
		Integer fromBefore = SweBank.getBalance("Bob");
		Integer toBefore = SweBank.getBalance("Ulrika");
		SweBank.addTimedPayment("Bob", "1", 1, 0, new Money(5000, SEK), SweBank, "Ulrika");
		SweBank.tick();
		assertEquals(Integer.valueOf(fromBefore - 5000), SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(toBefore + 5000), SweBank.getBalance("Ulrika"));
	}
}
