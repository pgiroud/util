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
     * Arrondi au 0,0001 c.-à-d. 1e-4 le plus proche.
     */
    DIX_MILLIEME_LE_PLUS_PROCHE(new BigDecimal("0.0001"), RoundingMode.HALF_UP, 4),

    /**
     * Arrondi au 0,0001 c.-à-d. 1e-4 le plus proche.
     */
    DIX_MILLIEME_INFERIEUR(new BigDecimal("0.0001"), RoundingMode.DOWN, 4),

    /**
     * Arrondi au 0,00001 c.-à-d. 1e-5 le plus proche.
     */
    CENT_MILLIEME_LE_PLUS_PROCHE(new BigDecimal("0.00001"), RoundingMode.HALF_UP, 5),

    /**
     * Arrondi au 0,00001 c.-à-d. 1e-5 le plus proche.
     */
    CENT_MILLIEME_INFERIEUR(new BigDecimal("0.00001"), RoundingMode.DOWN, 5),

    /**
     * Arrondi au millième le plus proche.
     */
    MILLIEME_LE_PLUS_PROCHE(new BigDecimal("0.001"), RoundingMode.HALF_UP, 3),

    /**
     * Arrondi au centième inférieur. Par exemple, 2.288 sera
     * arrondi à 2.28
     */
    CENTIEME_INF(BigDecimalUtil.UN_CENTIEME, RoundingMode.DOWN, 2),

    /**
     * Arrondi au centième le plus proche.
     */
    CENTIEME_LE_PLUS_PROCHE(BigDecimalUtil.UN_CENTIEME, RoundingMode.HALF_UP, 2),

    /**
     * Arrondi au centième supérieur.
     */
    CENTIEME_SUP(BigDecimalUtil.UN_CENTIEME, RoundingMode.UP, 2),

    /**
     * Arrondi au vingtième inférieur (équivalent à 5 centièmes). Par exemple, 2.28 sera
     * arrondi à 2.25.
     */
    VINGTIEME_INF(BigDecimalUtil.CINQ_CENTIEMES, RoundingMode.DOWN, 2),

    /**
     * Arrondi au vingtième supérieur (équivalent à 5 centièmes). Par exemple, 2.28 sera arrondi
     * à 2.30.
     */
    VINGTIEME_SUP(BigDecimalUtil.CINQ_CENTIEMES, RoundingMode.UP, 2),


    /**
     * Arrondi au vintième le plus proche ce qui est équivalent à arrondi
     * aux cinq centièmes les plus proches. Par exemple
     * <ul>
     *  <li>2.28 sera arrondi à 2.30</li>
     *  <li>2.27 sera arrondi à 2.25</li>
     *  <li>2.275 sera arrondi à 2.30</li>
     * </ul>
     */
    VINGTIEME_LE_PLUS_PROCHE(BigDecimalUtil.CINQ_CENTIEMES, RoundingMode.HALF_UP, 2),

    /**
     * Arrondi au dixième inférieur
     */
    DIXIEME_INF(BigDecimalUtil.UN_DIXIEME_AVEC_2_DECIMALES, RoundingMode.DOWN, 2),


    /**
     * Arrondi au dixième supérieur
     */
    DIXIEME_SUP(BigDecimalUtil.UN_DIXIEME_AVEC_2_DECIMALES, RoundingMode.UP, 2),


    /**
     * Arrondi au dixième le plus proche.
     */
    DIXIEME_LE_PLUS_PROCHE(BigDecimalUtil.UN_DIXIEME_AVEC_2_DECIMALES, RoundingMode.HALF_UP, 2),


    /**
     * Arrondi à l’unité inférieure.
     */
    UNITE_INF(BigDecimalUtil.UN, RoundingMode.DOWN),


    /**
     * Arrondi à l’unité supérieure.
     */
    UNITE_SUP(BigDecimalUtil.UN, RoundingMode.UP),


    /**
     * Arrondi à l’unité la plus proche
     */
    UNITE_LA_PLUS_PROCHE(BigDecimalUtil.UN, RoundingMode.HALF_UP),


    /**
     * Arrondi à la dizaine inférieure
     */
    DIZAINE_INF(BigDecimalUtil.DIX, RoundingMode.DOWN),


    /**
     * Arrondi à la dizaine supérieure
     */
    DIZAINE_SUP(BigDecimalUtil.DIX, RoundingMode.UP),


    /**
     * Arrondi à la dizaine la plus proche.
     */
    DIZAINE_LA_PLUS_PROCHE(BigDecimalUtil.DIX, RoundingMode.HALF_UP),


    /**
     * Arrondi à la centaine inférieure
     */
    CENTAINE_INF(BigDecimalUtil.CENT, RoundingMode.DOWN),


    /**
     * Arrondi à la centaine supérieure
     */
    CENTAINE_SUP(BigDecimalUtil.CENT, RoundingMode.UP),


    /**
     * Arrondi à la centaine la plus proche.
     */
    CENTAINE_LA_PLUS_PROCHE(BigDecimalUtil.CENT, RoundingMode.HALF_UP),

    /**
     * Arrondi au millier inférieur
     */
    MILLE_INF(BigDecimalUtil.MILLE, RoundingMode.DOWN),


    /**
     * Arrondi au millier supérieur
     */
    MILLE_SUP(BigDecimalUtil.MILLE, RoundingMode.UP),


    /**
     * Arrondi au millier le plus proche.
     */
    MILLE_LE_PLUS_PROCHE(BigDecimalUtil.MILLE, RoundingMode.HALF_UP),


    ///
    /// Arrondi utilisé par l’Administration Fédérale des Contributions dans les barèmes IFD de l’impôt à la source (barème à tranche constante)
    /// * 3317 est arrondi à 3325
    /// * 3303 est arrondi à 3325
    /// * 3300 est arrondi à 3275
    /// * 3299 est arrondi à 3275
    ///
    CINQUANTE_UNITE_DECALE_VINGT_CINQ(BigDecimal.valueOf(50), RoundingMode.HALF_DOWN, 0, BigDecimal.valueOf(25));

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

    public BigDecimal precision() {
        return this.precision;
    }

    /**
     * Arrondi le montant fourni en paramètre. Attention, le type BigDecimal étant immuable,
     * une nouvelle instance de BigDecimal est retournée.
     * Si le montant à arrondir est négatif, on retourne la valeur absolue arrondie multipliée par -1.
     * @param valeur la valeur à arrondir
     * @return la valeur arrondie.
     */
    public BigDecimal arrondir(BigDecimal valeur) {
        if (null == valeur) {
            return null;
        }
        BigDecimal montantAArrondirTranslate = valeur.subtract(offset);
        BigDecimal normalise = montantAArrondirTranslate.divide(precision, PRECISION_ARRONDI, mode);
        return normalise.setScale(0, mode).multiply(precision).setScale(scale).add(offset);
    }

}
