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
    MILLIEME_LE_PLUS_PROCHE(new BigDecimal("0.001"), RoundingMode.HALF_UP, 3),

    /**
     * Arrondi au centime inférieur. Par exemple, 2.288 sera
     * arrondi à 2.28
     */
    CENTIEME_INF(BigDecimalUtil.UN_CENTIEME, RoundingMode.DOWN, 2),

    /**
     * Arrondi au centime le plus proche.
     */
    CENTIEME_LE_PLUS_PROCHE(BigDecimalUtil.UN_CENTIEME, RoundingMode.HALF_UP, 2),

    /**
     * Arrondi au centime supérieur.
     */
    CENTIEME_SUP(BigDecimalUtil.UN_CENTIEME, RoundingMode.UP, 2),

    /**
     * Arrondi aux cinq centimes inférieur. Par exemple, 2.28 sera
     * arrondi à 2.25
     */
    CINQ_CENTIEMES_INF(BigDecimalUtil.CINQ_CENTIEMES, RoundingMode.DOWN, 2),


    /**
     * Arrondi aux cinq centimes supérieurs. Par exemple, 2.28 sera arrondi
     * à 2.30
     */
    CINQ_CENTIEMES_SUP(BigDecimalUtil.CINQ_CENTIEMES, RoundingMode.UP, 2),


    /**
     * Arrondi aux cinq centimes les plus proches. Par exemple
     * <ul>
     *  <li>2.28 sera arrondi à 2.30</li>
     *  <li>2.27 sera arrondi à 2.25</li>
     *  <li>2.275 sera arrondi à 2.30</li>
     * </ul>
     */
    CINQ_CENTIEMES_LES_PLUS_PROCHES(BigDecimalUtil.CINQ_CENTIEMES, RoundingMode.HALF_UP, 2),

    VINGTIEME_LE_PLUS_PROCHE(BigDecimalUtil.CINQ_CENTIEMES, RoundingMode.HALF_UP, 2),

    /**
     * Arrondi aux dix centimes inférieur
     */
    DIXIEME_INF(BigDecimalUtil.UN_DIXIEME_AVEC_2_DECIMALES, RoundingMode.DOWN, 2),


    /**
     * Arrondi aux dix centimes supérieurs
     */
    DIXIEME_SUP(BigDecimalUtil.UN_DIXIEME_AVEC_2_DECIMALES, RoundingMode.UP, 2),


    /**
     * Arrondi aux dix centimes les plus proches.
     */
    DIXIEME_LE_PLUS_PROCHE(BigDecimalUtil.UN_DIXIEME_AVEC_2_DECIMALES, RoundingMode.HALF_UP, 2),


    /**
     * Arrondi au franc inférieur.
     */
    UNITE_INF(BigDecimalUtil.UN, RoundingMode.DOWN),


    /**
     * Arrondi au franc supérieur.
     */
    UNITE_SUP(BigDecimalUtil.UN, RoundingMode.UP),


    /**
     * Arrondi au franc le plus proche
     */
    UNITE_LA_PLUS_PROCHE(BigDecimalUtil.UN, RoundingMode.HALF_UP),


    /**
     * Arrondi aux dix francs inférieurs
     */
    DIZAINE_INF(BigDecimalUtil.DIX, RoundingMode.DOWN),


    /**
     * Arrondi au dix francs supérieurs
     */
    DIZAINE_SUP(BigDecimalUtil.DIX, RoundingMode.UP),


    /**
     * Arrondi aux dix francs les plus proches.
     */
    DIZAINE_LA_PLUS_PROCHE(BigDecimalUtil.DIX, RoundingMode.HALF_UP),


    /**
     * Arrondi aux cents francs inférieurs
     */
    CENTAINE_INF(BigDecimalUtil.CENT, RoundingMode.DOWN),


    /**
     * Arrondi aux cents francs supérieurs
     */
    CENTAINE_SUP(BigDecimalUtil.CENT, RoundingMode.UP),


    /**
     * Arrondi aux cents francs les plus proches.
     */
    CENTAINE_LA_PLUS_PROCHE(BigDecimalUtil.CENT, RoundingMode.HALF_UP),

    /**
     * Arrondi aux milles francs inférieurs
     */
    MILLE_INF(BigDecimalUtil.MILLE, RoundingMode.DOWN),


    /**
     * Arrondi aux milles francs supérieurs
     */
    MILLE_SUP(BigDecimalUtil.MILLE, RoundingMode.UP),


    /**
     * Arrondi aux milles francs les plus proches.
     */
    MILLE_LE_PLUS_PROCHE(BigDecimalUtil.MILLE, RoundingMode.HALF_UP),


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
     * @param inoMontantAArrondir le montant à arrondir
     * @return Une nouvelle instance de CsMontant : le montant arrondi.
     */
    @Deprecated
    public BigDecimal arrondirMontant(BigDecimal inoMontantAArrondir) {
        if (null == inoMontantAArrondir) {
            return null;
        }
        BigDecimal montantAArrondirTranslate = inoMontantAArrondir.subtract(offset);
        BigDecimal normalise = montantAArrondirTranslate.divide(precision, PRECISION_ARRONDI, mode);
        return normalise.setScale(0, mode).multiply(precision).setScale(scale).add(offset);
    }

    public BigDecimal arrondir(BigDecimal valeur) {
        if (null == valeur) {
            return null;
        }
        BigDecimal montantAArrondirTranslate = valeur.subtract(offset);
        BigDecimal normalise = montantAArrondirTranslate.divide(precision, PRECISION_ARRONDI, mode);
        return normalise.setScale(0, mode).multiply(precision).setScale(scale).add(offset);
    }

}
