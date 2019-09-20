package idwall.desafio.string.formatter;

import idwall.desafio.string.formatter.abstracts.SizeFormatter;

public class SpliterSizeFormatter extends SizeFormatter {

    public SpliterSizeFormatter(int lineSize) {
        super(lineSize);
    }

    @Override
    public String format(String text) {
        StringBuilder builder = new StringBuilder();

        int length = 0;

        String[] splitText = text.split("(?=[ \\n])");

        for (int i = 0; i < splitText.length; i++) {
            String s = splitText[i];
            int lineLength = s.length();

            if ((length <= 0) && s.startsWith(" ")) {
                builder.append(s.substring(1));
            } else {
                builder.append(s);
            }

            if (!s.equals("\n")) {
                length += lineLength;
            } else if (s.startsWith("\n")) {
                length = -1;
            } else {
                length = 0;
            }

            int index = i + 1;

            if (index < splitText.length) {

                String next = splitText[index];
                if ((length + next.length()) > getLineSize()) {
                    length = 0;
                    builder.append('\n');
                }
            }
        }
        return builder.toString();
    }
}