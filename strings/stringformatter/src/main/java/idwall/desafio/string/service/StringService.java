package idwall.desafio.string.service;

import idwall.desafio.string.formatter.JustifyFormatter;
import idwall.desafio.string.formatter.SpliterSizeFormatter;
import idwall.desafio.string.formatter.interfaces.StringFormatter;
import org.springframework.stereotype.Service;

@Service
public class StringService {

    public String textFormat(String text, int width, boolean justify) {
        StringFormatter formatter = new SpliterSizeFormatter(width);

        if (justify) {
            formatter = formatter.otherFormat(new JustifyFormatter(width));
        }

        return formatter.format(text);
    }
}
