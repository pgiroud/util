package ch.ge.afc.util;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.Assert.*;

import org.junit.Test;

public class BigDecimalUtilTest {

	private static final Locale SUISSE_ROMANDE = new Locale("fr","CH");
	
	@Test
	public void formattage() {
		NumberFormat format = NumberFormat.getInstance(SUISSE_ROMANDE);
		assertEquals("Format sur 5 positions avant la virgule","   12.346",BigDecimalUtil.formatRight(new BigDecimal("12.346"), format, 5));
		assertEquals("Format sur 5 positions avant la virgule","    0.01",BigDecimalUtil.formatRight(new BigDecimal("0.01"), format, 5));
		assertEquals("Format sur 5 positions avant la virgule","    0",BigDecimalUtil.formatRight(new BigDecimal("0"), format, 5));
		// La suppression des 00 après la virgule est due au format et non à la méthode
		assertEquals("Format sur 5 positions avant la virgule","    0",BigDecimalUtil.formatRight(new BigDecimal("0.00"), format, 5));
	}
}
