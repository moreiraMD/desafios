package idwall.desafio.string.formatter;

import idwall.desafio.string.formatter.abstracts.SizeFormatter;

import java.util.stream.IntStream;

public class JustifyFormatter extends SizeFormatter {
    public JustifyFormatter(int lineSize) {
        super(lineSize);
    }

    @Override
    public String format(String text) {
        StringBuilder builder = new StringBuilder();

        String[] split = text.split("(?=\\n)");

        for (String line : split) {
            String cleanLine = line.replace("\n", "");

            String[] cleanVector = toArray(cleanLine);

            String justifyLine = fullJustify(cleanVector, this.getLineSize());

            builder.append(justifyLine)
                    .append("\n");
        }

        return builder.toString();
    }

    private String[] toArray(String line) {
        String[] split = line.split("(?=[ \\n])");

        IntStream.range(0, split.length).forEach(i -> {
            String s = split[i];
            split[i] = s.replace(" ", "");
        });

        return split;
    }

    private String fullJustify(String[] words, int maxWidth) {
        StringBuilder res = new StringBuilder();
        int start = 0;

        while( start < words.length) {
            int[] tmp = cut(words, start, maxWidth);
            int end = tmp[0];
            int space = tmp[1];

            res.append(justifyLine(words, start, end, space));

            start = end + 1;
        }

        return res.toString();
    }

    private int[] cut(String[] words, int start, int maxWidth) {
        int len = words[start].length();
        int charLen = len;
        int end = start;

        while(true) {
            if(end == words.length - 1 || len + 1 + words[end + 1].length() > maxWidth) {
                return new int[]{end, maxWidth - charLen};
            }

            end++;
            len += 1 + words[end].length();
            charLen += words[end].length();
        }
    }

    private String justifyLine(String[] words, int start, int end, int space) {
        StringBuilder sb = new StringBuilder();
        sb.append(words[start]);

        if(start == end) {
            for(int i = 0 ; i < space ; i++) {
                sb.append(' ');
            }
            return sb.toString();
        }

        int gap = space / (end - start);
        int leftNum = space % (end - start);
        int i = 0;

        while(i < leftNum) {
            sb.append(" ".repeat(Math.max(0, gap + 1)));
            sb.append(words[start + i + 1]);
            i++;
        }

        while(start + i + 1 <= end) {
            sb.append(" ".repeat(Math.max(0, gap)));
            sb.append(words[start + i + 1]);
            i++;
        }

        return sb.toString();
    }
}
