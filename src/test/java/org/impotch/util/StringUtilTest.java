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
package org.impotch.util;


import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

	@Test
	public void hasTexte() {
		assertFalse("Null",StringUtil.hasText(null));
		assertFalse("Chaîne vide",StringUtil.hasText(""));
		assertFalse("Un espace",StringUtil.hasText(" "));
		assertFalse("Plusieurs espaces",StringUtil.hasText("  "));
		assertFalse("Plusieurs espaces insécables",StringUtil.hasText("\u00A0\u00A0\u00A0\u00A0"));
		assertTrue("Texte avec un seul caractère",StringUtil.hasText("a"));
		assertTrue("Texte avec plusieurs caractères",StringUtil.hasText("abcéà"));
		assertTrue("Texte avec plusieurs caractères précédés de blanc",StringUtil.hasText("  abcéà"));
	}
}
