/**
 * This file is part of impotch/util.
 *
 * impotch/util is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * impotch/util is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with impotch/util.  If not, see <http://www.gnu.org/licenses/>.
 */package org.impotch.util;


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
