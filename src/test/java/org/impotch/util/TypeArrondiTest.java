package org.impotch.util;
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


import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TypeArrondiTest {

    @Test
    public void arrondirNull() {
        assertThat(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES.arrondirMontant(null)).isNull();
    }

    @Test
    public void arrondiCinqCentimes() {
        // Arrondi de 0 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "0", "0.00");
        // Arrondi de 0.0001253 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "0.0001253", "0.00");
        // Arrondi de -0.01 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "-0.01", "0.00");
        // Arrondi de 0.25 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "0.25", "0.25");
        // Arrondi de -0.25 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "-0.25", "-0.25");
        // Arrondi de 1.0 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "1.0", "1.00");
        // Arrondi de 1.24356 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "1.24356", "1.25");
        // Arrondi de -1.24356 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "-1.24356", "-1.25");
        // Arrondi de 2.5 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "2.5", "2.50");
        // Arrondi de 10090 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "10090", "10090.00");
        // Arrondi de 12875.243 au 5 centimes le plus proche
        testArrondi(TypeArrondi.CINQ_CENTIEMES_LES_PLUS_PROCHES, "12875.243", "12875.25");
    }

    @Test
    public void arrondiCinqCentimesSuperieures() {
        // Arrondi de 0 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "0", "0.00");
        // Arrondi de 0.0001253 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "0.0001253", "0.05");
        // Arrondi de -0.0001253 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "-0.0001253", "-0.05");
        // Arrondi de 0.25 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "0.25", "0.25");
        // Arrondi de 1.0 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "1.0", "1.00");
        // Arrondi de 1.24356 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "1.24356", "1.25");
        // Arrondi de 2.5 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "2.5", "2.50");
        // Arrondi de 10090 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "10090", "10090.00");
        // Arrondi de 12875.243 au 5 centimes supérieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_SUP, "12875.243", "12875.25");
    }


    @Test
    public void arrondiCinqCentimesInferieures() {
        // Arrondi de 0 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "0", "0.00");
        // Arrondi de 0.0001253 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "0.0001253", "0.00");
        // Arrondi de -0.0001253 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "-0.0001253", "0.00");
        // Arrondi de 0.25 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "0.25", "0.25");
        // Arrondi de 1.0 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "1.0", "1.00");
        // Arrondi de 1.24356 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "1.24356", "1.20");
        // Arrondi de -1.24356 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "-1.24356", "-1.20");
        // Arrondi de 2.5 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "2.5", "2.50");
        // Arrondi de 10090 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "10090", "10090.00");
        // Arrondi de 12875.243 au 5 centimes inférieur
        testArrondi(TypeArrondi.CINQ_CENTIEMES_INF, "12875.243", "12875.20");
    }

    @Test
    public void arrondiDixCentimes() {
        // Arrondi de 0 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIXIEME_LE_PLUS_PROCHE, "0", "0.00");
        // Arrondi de 0.0001253 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIXIEME_LE_PLUS_PROCHE, "0.0001253", "0.00");
        // Arrondi de 0.25 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIXIEME_LE_PLUS_PROCHE, "0.25", "0.30");
        // Arrondi de 1.0 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIXIEME_LE_PLUS_PROCHE, "1.0", "1.00");
        // Arrondi de 1.24356 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIXIEME_LE_PLUS_PROCHE, "1.24356", "1.20");
        // Arrondi de 2.5 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIXIEME_LE_PLUS_PROCHE, "2.5", "2.50");
        // Arrondi de 10090 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIXIEME_LE_PLUS_PROCHE, "10090", "10090.00");
        // Arrondi de 12875.243 au 10 centimes le plus proche
        testArrondi(TypeArrondi.DIXIEME_LE_PLUS_PROCHE, "12875.243", "12875.20");
    }

    @Test
    public void arrondiDixCentimesSuperieures() {
        // Arrondi de 0 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIXIEME_SUP, "0", "0.00");
        // Arrondi de 0.0001253 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIXIEME_SUP, "0.0001253", "0.10");
        // Arrondi de 0.25 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIXIEME_SUP, "0.25", "0.30");
        // Arrondi de 1.0 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIXIEME_SUP, "1.0", "1.00");
        // Arrondi de 1.24356 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIXIEME_SUP, "1.24356", "1.30");
        // Arrondi de 2.5 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIXIEME_SUP, "2.5", "2.50");
        // Arrondi de 10090 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIXIEME_SUP, "10090", "10090.00");
        // Arrondi de 12875.243 au 10 centimes supérieur
        testArrondi(TypeArrondi.DIXIEME_SUP, "12875.243", "12875.30");
    }

    @Test
    public void cinquanteFrancAvecOffsetVingtCinq() {
        // arrondi de 3317
        testArrondi(TypeArrondi.CINQUANTE_UNITE_DECALE_VINGT_CINQ, "3317", "3325");
        testArrondi(TypeArrondi.CINQUANTE_UNITE_DECALE_VINGT_CINQ, "3303", "3325");
        testArrondi(TypeArrondi.CINQUANTE_UNITE_DECALE_VINGT_CINQ, "3300", "3275");
        testArrondi(TypeArrondi.CINQUANTE_UNITE_DECALE_VINGT_CINQ, "3299", "3275");
    }


    private void testArrondi(TypeArrondi typeArrondi, String valeur, String valeurAttendue) {
        assertThat(typeArrondi.arrondir(new BigDecimal(valeur))).isEqualTo(new BigDecimal(valeurAttendue));
    }

}
