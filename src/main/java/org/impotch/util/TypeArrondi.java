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
import java.math.RoundingMode;

/**
 * Énumération des types d'arrondi.
 * Un type d'arrondi sert à arrondir un décimal.
 */
public enum TypeArrondi {


    /**************************************************/
    /***********Instances de l'énumération ************/
    /**************************************************/

    /**
     * Arrondi au dixième de centime le plus proche.
     */
    DIXIEME_CT(new BigDecimal("0.001"), RoundingMode.HALF_UP, 3),

    /**
     * Arrondi au centime inférieur. Par exemple, 2.288 sera
     * arrondi à 2.28
     */
    CT_INF(BigDecimalUtil.UN_CENTIME, RoundingMode.DOWN, 2),

    /**
     * Arrondi au centime le plus proche.
     */
    CT(BigDecimalUtil.UN_CENTIME, RoundingMode.HALF_UP, 2),

    /**
     * Arrondi au centime supérieur.
     */
    CT_SUP(BigDecimalUtil.UN_CENTIME, RoundingMode.UP, 2),

    /**
     * Arrondi aux cinq centimes inférieur. Par exemple, 2.28 sera
     * arrondi à 2.25
     */
    CINQ_CTS_INF(BigDecimalUtil.CINQ_CTS, RoundingMode.DOWN, 2),


    /**
     * Arrondi aux cinq centimes supérieurs. Par exemple, 2.28 sera arrondi
     * à 2.30
     */
    CINQ_CTS_SUP(BigDecimalUtil.CINQ_CTS, RoundingMode.UP, 2),


    /**
     * Arrondi aux cinq centimes les plus proches. Par exemple
     * <ul>
     *  <li>2.28 sera arrondi à 2.30</li>
     *  <li>2.27 sera arrondi à 2.25</li>
     *  <li>2.275 sera arrondi à 2.30</li>
     * </ul>
     */
    CINQ_CTS(BigDecimalUtil.CINQ_CTS, RoundingMode.HALF_UP, 2),


    /**
     * Arrondi aux dix centimes inférieur
     */
    DIX_CTS_INF(BigDecimalUtil.DIX_CTS, RoundingMode.DOWN, 2),


    /**
     * Arrondi aux dix centimes supérieurs
     */
    DIX_CTS_SUP(BigDecimalUtil.DIX_CTS, RoundingMode.UP, 2),


    /**
     * Arrondi aux dix centimes les plus proches.
     */
    DIX_CTS(BigDecimalUtil.DIX_CTS, RoundingMode.HALF_UP, 2),


    /**
     * Arrondi au franc inférieur.
     */
    FRANC_INF(BigDecimalUtil.UN, RoundingMode.DOWN),


    /**
     * Arrondi au franc supérieur.
     */
    FRANC_SUP(BigDecimalUtil.UN, RoundingMode.UP),


    /**
     * Arrondi au franc le plus proche
     */
    FRANC(BigDecimalUtil.UN, RoundingMode.HALF_UP),


    /**
     * Arrondi aux dix francs inférieurs
     */
    DIX_FRANC_INF(BigDecimalUtil.DIX, RoundingMode.DOWN),


    /**
     * Arrondi au dix francs supérieurs
     */
    DIX_FRANC_SUP(BigDecimalUtil.DIX, RoundingMode.UP),


    /**
     * Arrondi aux dix francs les plus proches.
     */
    DIX_FRANC(BigDecimalUtil.DIX, RoundingMode.HALF_UP),


    /**
     * Arrondi aux cents francs inférieurs
     */
    CENT_FRANC_INF(BigDecimalUtil.CENT, RoundingMode.DOWN),


    /**
     * Arrondi aux cents francs supérieurs
     */
    CENT_FRANC_SUP(BigDecimalUtil.CENT, RoundingMode.UP),


    /**
     * Arrondi aux cents francs les plus proches.
     */
    CENT_FRANC(BigDecimalUtil.CENT, RoundingMode.HALF_UP),

    /**
     * Arrondi aux milles francs inférieurs
     */
    MILLE_FRANC_INF(BigDecimalUtil.MILLE, RoundingMode.DOWN),


    /**
     * Arrondi aux milles francs supérieurs
     */
    MILLE_FRANC_SUP(BigDecimalUtil.MILLE, RoundingMode.UP),


    /**
     * Arrondi aux milles francs les plus proches.
     */
    MILLE_FRANC(BigDecimalUtil.MILLE, RoundingMode.HALF_UP),


    CINQUANTE_FRANC_DECALE_VINGT_CINQ(BigDecimal.valueOf(50), RoundingMode.HALF_DOWN, 0, BigDecimal.valueOf(25));

    private static final int PRECISION_ARRONDI = 10;

    /**************************************************/
    /******************* Attributs ********************/
    /**************************************************/

    private final BigDecimal precision;
    private final RoundingMode mode;
    private final int scale;
    private final BigDecimal offset;

    /**************************************************/
    /**************** Constructeurs *******************/
    /**************************************************/

    /**
     * Construction d'un type d'arrondi en fournissant une précision, par exemple 0.05 pour un arrondi
     * aux cinq centimes, et un mode d'arrondi (arrondi au plus près, à la valeur supérieure ou inférieure)
     * @param precision la précision : 0.05 pour un arrondi aux cinq centimes par exemple
     * @param mode le mode d'arrondi i.e. au plus près, à la valeur supérieure ou inférieure
     */
    TypeArrondi(BigDecimal precision, RoundingMode mode) {
        this.precision = precision;
        this.mode = mode;
        scale = 0;
        offset = BigDecimal.ZERO;
    }

    /**
     * Construction d'un type d'arrondi en fournissant une précision, par exemple 0.05 pour un arrondi
     * aux cinq centimes, et un mode d'arrondi (arrondi au plus près, à la valeur supérieure ou inférieure)
     * @param precision la précision : 0.05 pour un arrondi aux cinq centimes par exemple
     * @param mode le mode d'arrondi i.e. au plus près, à la valeur supérieure ou inférieure
     * @param nbChiffreApresVirgule le nombre de chiffre après la virgule du résultat. Par exemple, pour un 
     * arrondi à 10 cts près, on voudra 2 chiffres après la virgule
     */
    TypeArrondi(BigDecimal precision, RoundingMode mode, int nbChiffreApresVirgule) {
        this(precision, mode, nbChiffreApresVirgule, BigDecimal.ZERO);
    }


    TypeArrondi(BigDecimal precision, RoundingMode mode, int nbChiffreApresVirgule, BigDecimal offset) {
        this.precision = precision;
        this.mode = mode;
        scale = nbChiffreApresVirgule;
        this.offset = offset;
    }

    /**************************************************/
    /******************* Méthodes *********************/
    /**************************************************/

    /**
     * Arrondi le montant fourni en paramètre. Attention, le type BigDecimal étant immuable,
     * une nouvelle instance de BigDecimal est retournée.
     * Si le montant à arrondir est négatif, on retourne la valeur absolue arrondie multipliée par -1.
     * @param inoMontantAArrondir le montant à arrondir
     * @return Une nouvelle instance de CsMontant : le montant arrondi.
     */
    public BigDecimal arrondirMontant(BigDecimal inoMontantAArrondir) {
        if (null == inoMontantAArrondir) {
            return null;
        }
        BigDecimal montantAArrondirTranslate = inoMontantAArrondir.subtract(offset);
        BigDecimal normalise = montantAArrondirTranslate.divide(precision, PRECISION_ARRONDI, mode);
        return normalise.setScale(0, mode).multiply(precision).setScale(scale).add(offset);
    }

}
