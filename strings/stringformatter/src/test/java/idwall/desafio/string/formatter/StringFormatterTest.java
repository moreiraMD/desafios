package idwall.desafio.string.formatter;

import idwall.desafio.string.formatter.interfaces.StringFormatter;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class StringFormatterTest {

    private static final String DELIMITER = "\n";
    private static final String INPUT = result("input.txt");
    private static final String OUTPUT_1 = result("output-parte1.txt");
    private static final String OUTPUT_2 = result("output-parte2.txt");
    private static final int LINE_SIZE = 40;

    private static String getString(InputStream input, Charset charset) {
        return new BufferedReader(new InputStreamReader(input, charset))
                .lines()
                .collect(Collectors.joining(DELIMITER));
    }

    private static String result(String result) {
        return getString(StringFormatterTest.class.getClassLoader().getResourceAsStream(result), StandardCharsets.UTF_8);
    }

    @Test
    public void spliterSizeFormatterTest() {
        StringFormatter formatter = new SpliterSizeFormatter(LINE_SIZE);

        String text = formatter.format(INPUT);

        assertEquals(OUTPUT_1, text);
    }

    @Test
    public void justifyFormatterTest() {
        StringFormatter formatter = new SpliterSizeFormatter(LINE_SIZE)
                .otherFormat(JustifyFormatter::new);

        String text = formatter.format(INPUT);

        assertEquals(OUTPUT_2, text);
    }
}
