package ch.ge.afc.util;


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
