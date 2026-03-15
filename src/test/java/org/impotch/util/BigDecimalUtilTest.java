/*
 * This file is part of impotch/util.
 * <p>
 * impotch/util is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * impotch/util is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with impotch/util.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.impotch.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.impotch.util.BigDecimalUtil.formatRight;

public class BigDecimalUtilTest {

    private static final Locale SUISSE_ROMANDE = Locale.of("fr", "CH");

    @Test
    public void formattage() {
        NumberFormat format = NumberFormat.getInstance(SUISSE_ROMANDE);
        assertThat(formatRight(new BigDecimal("12.346"), format, 5)).isEqualTo("   12,346");
        assertThat(formatRight(new BigDecimal("0.01"), format, 5)).isEqualTo("    0,01");
        assertThat(formatRight(new BigDecimal("0"), format, 5)).isEqualTo("    0");
        // La suppression des 00 après la virgule est due au format et non à la méthode
        assertThat(formatRight(new BigDecimal("0.00"), format, 5)).isEqualTo("    0");
    }

    @Test
    public void parseNombreAVirgule() {
       assertThat(BigDecimalUtil.parse("3,14")).isEqualTo("3.14");
    }

    @Test
    public void parseNombreSeparateurMillier() {
       assertThat(BigDecimalUtil.parse("3 141")).isEqualTo("3141");	
    }

    @Test
    public void parseNombreAVirguleEtSeparateurMillier() {
       assertThat(BigDecimalUtil.parse("3 141,5927")).isEqualTo("3141.5927");		
    }
}
