package uk.gov.ch.util;

import org.apache.commons.lang3.StringUtils;

public class CustomTextFormat {

    private CustomTextFormat() {
    }

    /**
     * Formats the text case to a standard sentence formatting. First letter uppercase while the
     * rest lowercase.
     *
     * @param text The text to be formatted.
     * @return The formatted text {@link String}.
     */
    public static String formatStandardSentence(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String sub : text.toLowerCase().split("\\. ")) {
            stringBuilder.append(". ").append(StringUtils.capitalize(sub));
        }
        return stringBuilder.delete(0, 1).toString().trim();
    }
}
