package uk.gov.ch.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomTextFormatTest {

    @Test
    void formatStandardSentenceUppercaseTextTest() {
        String uppercaseText = "FULL RIGHTS TO RECEIVE NOTICE OF, AND ATTEND VOTE AT GENERAL MEETINGS. ONE SHARE CARRIES ONE VOTE, AND FULL RIGHTS TO DIVIDENDS AND CAPITAL DISTRIBUTIONS (INCLUDING UPON WINDING UP).";
        String expectedText = "Full rights to receive notice of, and attend vote at general meetings. One share carries one vote, and full rights to dividends and capital distributions (including upon winding up).";

        String result = CustomTextFormat.formatStandardSentence(uppercaseText);

        assertEquals(expectedText, result);
    }

    @Test
    void formatStandardSentenceLowercaseTextTest() {
        String lowercaseText = "full rights to receive notice of, and attend vote at general meetings. one share carries one vote, and full rights to dividends and capital distributions (including upon winding up).";
        String expectedText = "Full rights to receive notice of, and attend vote at general meetings. One share carries one vote, and full rights to dividends and capital distributions (including upon winding up).";

        String result = CustomTextFormat.formatStandardSentence(lowercaseText);

        assertEquals(expectedText, result);
    }

    @Test
    void formatStandardSentenceNullTextTest() {
        String nullText = null;

        String result = CustomTextFormat.formatStandardSentence(nullText);

        assertEquals(null, result);
    }

    @Test
    void formatStandardSentenceZeroLengthTextTest() {
        String zeroLengthText = "";

        String result = CustomTextFormat.formatStandardSentence(zeroLengthText);

        assertEquals("", result);
    }
}