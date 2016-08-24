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

import org.junit.Test;

import static org.junit.Assert.*;


public class TypeArrondiTest {

    @Test
    public void arrondirNull() {
        assertNull("Arrondi d'un null", TypeArrondi.CINQ_CTS.arrondirMontant(null));
    }

    @Test
    public void arrondiCinqCentimes() {
        // Arrondi de 0 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "0", "0.00");
        // Arrondi de 0.0001253 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "0.0001253", "0.00");
        // Arrondi de -0.01 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "-0.01", "0.00");
        // Arrondi de 0.25 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "0.25", "0.25");
        // Arrondi de -0.25 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "-0.25", "-0.25");
        // Arrondi de 1.0 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "1.0", "1.00");
        // Arrondi de 1.24356 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "1.24356", "1.25");
        // Arrondi de -1.24356 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "-1.24356", "-1.25");
        // Arrondi de 2.5 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "2.5", "2.50");
        // Arrondi de 10090 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "10090", "10090.00");
        // Arrondi de 12875.243 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CTS, "12875.243", "12875.25");
    }

    @Test
    public void arrondiCinqCentimesSuperieures() {
        // Arrondi de 0 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "0", "0.00");
        // Arrondi de 0.0001253 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "0.0001253", "0.05");
        // Arrondi de -0.0001253 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "-0.0001253", "-0.05");
        // Arrondi de 0.25 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "0.25", "0.25");
        // Arrondi de 1.0 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "1.0", "1.00");
        // Arrondi de 1.24356 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "1.24356", "1.25");
        // Arrondi de 2.5 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "2.5", "2.50");
        // Arrondi de 10090 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "10090", "10090.00");
        // Arrondi de 12875.243 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CTS_SUP, "12875.243", "12875.25");
    }


    @Test
    public void arrondiCinqCentimesInferieures() {
        // Arrondi de 0 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "0", "0.00");
        // Arrondi de 0.0001253 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "0.0001253", "0.00");
        // Arrondi de -0.0001253 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "-0.0001253", "0.00");
        // Arrondi de 0.25 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "0.25", "0.25");
        // Arrondi de 1.0 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "1.0", "1.00");
        // Arrondi de 1.24356 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "1.24356", "1.20");
        // Arrondi de -1.24356 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "-1.24356", "-1.20");
        // Arrondi de 2.5 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "2.5", "2.50");
        // Arrondi de 10090 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "10090", "10090.00");
        // Arrondi de 12875.243 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CTS_INF, "12875.243", "12875.20");
    }

    @Test
    public void arrondiDixCentimes() {
        // Arrondi de 0 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIX_CTS, "0", "0.00");
        // Arrondi de 0.0001253 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIX_CTS, "0.0001253", "0.00");
        // Arrondi de 0.25 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIX_CTS, "0.25", "0.30");
        // Arrondi de 1.0 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIX_CTS, "1.0", "1.00");
        // Arrondi de 1.24356 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIX_CTS, "1.24356", "1.20");
        // Arrondi de 2.5 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIX_CTS, "2.5", "2.50");
        // Arrondi de 10090 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIX_CTS, "10090", "10090.00");
        // Arrondi de 12875.243 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIX_CTS, "12875.243", "12875.20");
    }

    @Test
    public void arrondiDixCentimesSuperieures() {
        // Arrondi de 0 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIX_CTS_SUP, "0", "0.00");
        // Arrondi de 0.0001253 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIX_CTS_SUP, "0.0001253", "0.10");
        // Arrondi de 0.25 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIX_CTS_SUP, "0.25", "0.30");
        // Arrondi de 1.0 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIX_CTS_SUP, "1.0", "1.00");
        // Arrondi de 1.24356 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIX_CTS_SUP, "1.24356", "1.30");
        // Arrondi de 2.5 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIX_CTS_SUP, "2.5", "2.50");
        // Arrondi de 10090 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIX_CTS_SUP, "10090", "10090.00");
        // Arrondi de 12875.243 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIX_CTS_SUP, "12875.243", "12875.30");
    }

    @Test
    public void cinquanteFrancAvecOffsetVingtCinq() {
        // arrondi de 3317
        testArrondi(TypeArrondi.CINQUANTE_FRANC_DECALE_VINGT_CINQ, "3317", "3325");
        testArrondi(TypeArrondi.CINQUANTE_FRANC_DECALE_VINGT_CINQ, "3303", "3325");
        testArrondi(TypeArrondi.CINQUANTE_FRANC_DECALE_VINGT_CINQ, "3300", "3275");
        testArrondi(TypeArrondi.CINQUANTE_FRANC_DECALE_VINGT_CINQ, "3299", "3275");
    }


    private void testArrondi(TypeArrondi inoTypeArrondi, String inoValeurAArrondi, String inoValeurAttendue) {
        assertEquals(new BigDecimal(inoValeurAttendue), inoTypeArrondi.arrondirMontant(new BigDecimal(inoValeurAArrondi)));
    }

}
