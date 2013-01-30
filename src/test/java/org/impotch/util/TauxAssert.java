package org.impotch.util;

import java.math.BigDecimal;
import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.data.Offset;

/**
 * Created with IntelliJ IDEA.
 * User: patrick
 * Date: 09/12/12
 * Time: 17:55
 * To change this template use File | Settings | File Templates.
 */
public class TauxAssert extends AbstractAssert<TauxAssert,BigDecimal> {

    public TauxAssert(BigDecimal actual) {
        super(actual, TauxAssert.class);
    }

    public static TauxAssert assertThat(BigDecimal actual) {
        return new TauxAssert(actual);
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

        // use of existing Fest assertions but replacing the error message (format specifier are supported)
        Assertions.assertThat(actual)
                //.overridingErrorMessage("Expected rate to be %1$s but was %2$s", taux, actualStr)
                .isEqualTo(tauxParse);
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
