package uk.gov.ch.util;

import org.codehaus.plexus.util.StringUtils;

public class CustomTextFormat {

    /**
     * Formats the text case to a standard sentence formatting. First letter uppercase while the rest lowercase.
     * @param text The text to be formatted.
     * @return The formatted text {@link String}.
     */
    public static String formatStandardSentence(String text) {
        if(text == null || text.isEmpty()) return text;

        String formatted = "";
        for (String sub : text.toLowerCase().split("\\. ")) {
            formatted = formatted + ". " + StringUtils.capitalizeFirstLetter(sub) ;
        }
        return formatted.substring(2).trim();
    }
}
