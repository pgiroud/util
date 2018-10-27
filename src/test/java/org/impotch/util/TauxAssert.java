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
 */
/**
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

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

public class TauxAssert extends AbstractAssert<TauxAssert, BigDecimal> {

    private BigDecimal tolerance = null;

    public TauxAssert(BigDecimal actual) {
        super(actual, TauxAssert.class);
    }

    public static TauxAssert assertThat(BigDecimal actual) {
        return new TauxAssert(actual);
    }

    public TauxAssert tolerance(String ecartTauxTolere) {
        tolerance = BigDecimalUtil.parseTaux(ecartTauxTolere);
        return this;
    }

    public TauxAssert isEqualTo(String taux) {
        isNotNull();

        BigDecimal tauxParse = BigDecimalUtil.parseTaux(taux);

        String actualStr = actual.toPlainString();
        if (taux.contains("%")) {
            actualStr = actual.movePointRight(2).toPlainString() + " %";
        } else if (taux.contains("‰")) {
            actualStr = actual.movePointRight(3).toPlainString() + " ‰";
        }

        if (null == tolerance) {
            Assertions.assertThat(actual)
                    // TODO PGI Voir pourquoi il y a un problème quand on met des
                    // taux en %
                    //.overridingErrorMessage("Le taux attendu devrait être %1$s mais est %2$s", taux, actualStr)
                    .isEqualTo(tauxParse);
        } else {
            Assertions.assertThat(actual)
                    //.overridingErrorMessage("Le taux attendu devrait être %1$s mais est %2$s", taux, actualStr)
                    .isCloseTo(tauxParse, Offset.offset(tolerance));
        }
        return this;

    }

    public TauxAssert isEqualByComparingTo(String taux) {
        isNotNull();

        BigDecimal tauxParse = BigDecimalUtil.parseTaux(taux);

        String actualStr = actual.toPlainString();
        if (taux.contains("%")) {
            actualStr = actual.movePointRight(2).toPlainString() + " %";
        } else if (taux.contains("‰")) {
            actualStr = actual.movePointRight(3).toPlainString() + " ‰";
        }

        // use of existing Fest assertions but replacing the error message (format specifier are supported)
        Assertions.assertThat(actual)
                //.overridingErrorMessage("Expected rate to be %s but was %s", taux, actualStr)
                .isEqualByComparingTo(tauxParse);
        return this;

    }

//    public static Offset<BigDecimal>  offset(String tauxOffest) {
//        return new Offset<BigDecimal>()
//    }

    protected boolean isEqualTo(BigDecimal actual, BigDecimal expected, Offset<BigDecimal> offset) {
        return offset.value.compareTo(expected.subtract(actual).abs()) >= 0;
    }


}
