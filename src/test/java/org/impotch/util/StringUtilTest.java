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


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilTest {

    @Test
    public void hasTexte() {
        assertThat(StringUtil.hasText(null)).isFalse();
        assertThat(StringUtil.hasText("")).isFalse();
        assertThat(StringUtil.hasText(" ")).isFalse();
        assertThat(StringUtil.hasText("  ")).isFalse();
        // Plusieurs espaces insécables
        assertThat(StringUtil.hasText("\u00A0\u00A0\u00A0\u00A0")).isFalse();

        assertThat(StringUtil.hasText("a")).isTrue();
        assertThat(StringUtil.hasText("abcéà")).isTrue();
        assertThat(StringUtil.hasText("  abcéà")).isTrue();
    }
}
