package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate(), 1e-9);
		assertEquals(Double.valueOf(0.20), DKK.getRate(), 1e-9);
		assertEquals(Double.valueOf(1.5), EUR.getRate(), 1e-9);
	}
	
	@Test
	public void testSetRate() {
		Currency USD = new Currency("USD", 1.0);
		USD.setRate(0.5);
		assertEquals(Double.valueOf(0.5), USD.getRate(), 1e-9);
	}
	
	@Test
	public void testUniversalValue() {
		assertEquals(Integer.valueOf(1500), SEK.universalValue(10000));
		assertEquals(Integer.valueOf(1500), EUR.universalValue(1000));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(Integer.valueOf(1000), EUR.valueInThisCurrency(10000, SEK));
	}

}
