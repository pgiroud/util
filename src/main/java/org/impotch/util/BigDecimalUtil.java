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
import java.text.FieldPosition;
import java.text.NumberFormat;

import static java.math.BigDecimal.ZERO;

public final class BigDecimalUtil {

    // *************************************************************
    // ******* CHAMPS STATIQUES
    // *************************************************************

    // Ces choix sont arbitraires. 15 chiffres significatifs devraient être
    // suffisant dans la plupart des cas.
    // Pour le mode d'arrondi, on pourra trouver une justification dans
    // http://www2.hursley.ibm.com/decimal/decifaq1.html#rounding.

    private static final int DEFAULT_PRECISION = 15;
    private static final int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_HALF_DOWN;

    private static final char POUR_MILLE = '\u2030';
    private static final int MULTIPLICATEUR_POUR_MILLE = 3;
    private static final int MULTIPLICATEUR_POUR_CENT = 2;
    private static final int PRECISION_POUR_INVERSION = 20;

    public static final BigDecimal UN_CENTIME = new BigDecimal("0.01");
    public static final BigDecimal CINQ_CTS = new BigDecimal("0.05");
    public static final BigDecimal DIX_CTS = new BigDecimal("0.10");
    public static final BigDecimal CENT = new BigDecimal("100");
    public static final BigDecimal MILLE = new BigDecimal("1000");

    /**
     * Champ constant représentant 0 avec 2 decimales soit 0.00.
     */
    public static final BigDecimal ZERO_2_DECIMALES = new BigDecimal("0.00");

    /**
     * Champ constant représentant 1 avec 2 decimales soit 1.00.
     */
    public static final BigDecimal UN = new BigDecimal("1.00");

    /**
     * Champ constant représentant 2 avec 2 decimales soit 2.00.
     */
    public static final BigDecimal DEUX = new BigDecimal("2.00");

    /**
     * Champ constant représentant 3 avec 2 decimales soit 3.00.
     */
    public static final BigDecimal TROIS = new BigDecimal("3.00");

    /**
     * Champ constant représentant 10 avec 2 decimales soit 10.00.
     */
    public static final BigDecimal DIX = new BigDecimal("10.00");

    /**
     * Champ constant représentant 1/4 avec 2 decimales soit 0.25.
     */
    public static final BigDecimal UN_QUART = new BigDecimal("0.25");

    /**
     * Champ constant représentant 1/2 avec 2 decimales soit 0.50
     */
    public static final BigDecimal UN_DEMI = new BigDecimal("0.50");

    /**
     * Champ constant représentant 1/3 avec 15 decimales. En général, on préfera
     * faire un division par 3 que multiplier par 1/3 sauf si les performances
     * doivent être considérées.
     */
    public static final BigDecimal UN_TIER;

    // *************************************************************
    // ******* MÉTHODES STATIQUES
    // *************************************************************

    static {
        UN_TIER = UN.divide(TROIS, DEFAULT_PRECISION, DEFAULT_ROUNDING_MODE);
    }

    /**
     * Cette méthode permet de formatter un BigDecimal avec un nombre de chiffre
     * maximum. Par exemple, si pNf formatte avec 2 chiffres après la virgule,
     * un appel à formatRight("12.346",pNf,5) retourne la String "   12.346"
     * c'est-à-dire qu'on a ajouté 3 blancs en début de chaîne. Ceci est
     * pratique pour l'alignement.
     *
     * @param pNombre
     *            Le BigDecimal qu'on veut formatter
     * @param pNf
     *            Le format à appliquer
     * @param pMaxNumberOfDigit
     *            Le nombre de chiffre avant la virgule.
     * @return La chaîne de caractère formatée.
     */
    public static String formatRight(BigDecimal pNombre, NumberFormat pNf,
                                     int pMaxNumberOfDigit) {
        StringBuffer buf = new StringBuffer();
        FieldPosition fpos = new FieldPosition(NumberFormat.INTEGER_FIELD);
        pNf.format(pNombre, buf, fpos);
        for (int i = 0; i < pMaxNumberOfDigit - fpos.getEndIndex(); ++i) {
            buf.insert(0, ' ');
        }
        return buf.toString();
    }

    /**
     * Cette méthode permet de formatter un BigDecimal. Par exemple, un appel à
     * <code>format(new BigDecimal("12.32534"),2)</code> retourne le BigDecimal
     * 12.33. Un appel à <code>format(new BigDecimal("12"),2)</code> retourne un
     * BigDecimal 12.00.
     * <p>
     * Pour l'arrondi, on utilise une méthode européenne i.e. arrondi de 0.5 =
     * 1.
     *
     * @param n
     *            le BigDecimal à formatter
     * @param prec
     *            la precision souhaitée.
     * @return Un BigDecimal formatté.
     */
    public static BigDecimal format(BigDecimal n, int prec) {
        return n.setScale(prec, DEFAULT_ROUNDING_MODE);
    }

    /**
     * Cette méthode permet de supprimer les 0 superflus. Exemple
     * trim("2.560000") --> 2.56
     *
     * @param pNombre
     *            Le nombre contenant les 0 superflus
     * @return Le même nombre sans les 0
     */
    public static BigDecimal trim(BigDecimal pNombre) {
        BigDecimal nombreTronque = pNombre;
        try {
            while (true) {
                nombreTronque = nombreTronque.setScale(pNombre.scale() - 1);
            }
        } catch (ArithmeticException e) // NOPMD by thirion on 20.12.06 14:39
        {
            // il n'existe plus d 0
        }
        return nombreTronque;
    }

    /**
     * Cette méthode permet de savoir si un BigDecimal est strictement positif.
     *
     * @param pNombre
     *            Le nombre à tester. S'il est NULL, la méthode renvoie false
     * @return true si le pNombre est non NULL et > 0.
     */
    public static boolean isStrictementPositif(BigDecimal pNombre) {
        return null != pNombre && 0 > ZERO.compareTo(pNombre);
    }

    /**
     * Cette méthode permet de savoir si un BigDecimal est strictement négatif.
     *
     * @param pNombre
     *            Le nombre à tester. S'il est NULL, la méthode renvoie false
     * @return true si le pNombre est non NULL et < 0.
     */
    public static boolean isStrictementNegatif(BigDecimal pNombre) {
        return null != pNombre && 0 < ZERO.compareTo(pNombre);
    }

    /**
     * Compare deux BigDecimal même si l'un des deux, voire les deux, est null.
     * On considère dans cette méthode que null est plus petit que n'importe
     * quel BigDecimal non nul.
     *
     * @param pPremierNombre
     *            Le premier nombre, peut être null.
     * @param pSecondNombre
     *            Le second nombre, peut être null.
     * @return un entier strictement positif si le premier nombre est
     *         strictement supérieur au second nombre, 0 si les deux nombres
     *         sont égaux et un entier strictement négatif dans les autres cas.
     */
    public static int nullSafeCompare(BigDecimal pPremierNombre,
                                      BigDecimal pSecondNombre) {
        if (pPremierNombre == pSecondNombre) {
            return 0;
        }
        if (null == pPremierNombre) {
            return -1;
        }
        if (null == pSecondNombre) {
            return 1;
        }
        return pPremierNombre.compareTo(pSecondNombre);
    }

    /**
     * Cette méthode retourne un taux à partir de sa représentation.
     * Dans la représentation sont supportés les taux en % et les taux en pour mille.
     * Ainsi l'appel de cette méthode avec comme paramètre "2.5 %" retournera le décimal 0.025
     * @param taux le taux sous forme de chaîne de caractère c.-à-d. un nombre, un nombre suivi d'un pour cent ou un nombre suivi d'un pour mille.
     * @return le taux sous forme de nombre.
     * @throws NumberFormatException si la chaîne de caractère n'est pas un taux.
     */
    public static BigDecimal parseTaux(String taux) {
        int multiplicateur = 0;
        String chaineDecimale = taux;
        if (StringUtil.hasText(taux)) {
            if (-1 < taux.indexOf(POUR_MILLE)) {
                multiplicateur = MULTIPLICATEUR_POUR_MILLE;
                chaineDecimale = chaineDecimale.substring(0,
                        taux.indexOf(POUR_MILLE)).trim();
            } else if (-1 < taux.indexOf('%')) {
                multiplicateur = MULTIPLICATEUR_POUR_CENT;
                chaineDecimale = chaineDecimale.substring(0, taux.indexOf('%'))
                        .trim();
            }
        }
        BigDecimal tauxBD = new BigDecimal(chaineDecimale);
        if (multiplicateur > 0) {
            tauxBD = tauxBD.movePointLeft(multiplicateur);
        }
        return tauxBD;
    }

    /**
     * Retourne l'inverse (au sens de la multiplication) du nombre fourni en paramètre.
     * @param pDecimal le nombre à inverser
     * @return l'inverse du nombre c.-à-d. le nombre u tel que 1 = u * pDecimal
     * @throws ArithmeticException si pDecimal est égal à 0
     */
    public static BigDecimal invert(BigDecimal pDecimal) {
        // On commence par supprimer les 0 superflus
        if (0 == ZERO.compareTo(pDecimal)) {
            throw new ArithmeticException();
        }
        return trim(UN.divide(pDecimal, PRECISION_POUR_INVERSION, BigDecimal.ROUND_HALF_UP));
    }

    public static NumberFormat getFormat(int nbDecimale) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(nbDecimale);
        format.setMinimumFractionDigits(nbDecimale);
        format.setGroupingUsed(true);
        return format;
    }


    private BigDecimalUtil() {
    }
}
