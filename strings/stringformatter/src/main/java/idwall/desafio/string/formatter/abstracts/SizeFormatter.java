package idwall.desafio.string.formatter.abstracts;

import idwall.desafio.string.formatter.interfaces.StringFormatter;

import java.util.function.IntFunction;

public abstract class SizeFormatter implements StringFormatter {

    private final int lineSize;

    public SizeFormatter(int lineSize) {
        this.lineSize = lineSize;
    }

    public StringFormatter otherFormat(IntFunction<StringFormatter> formatter) {
        StringFormatter stringFormatter = formatter.apply(this.getLineSize());
        return StringFormatter.super.otherFormat(stringFormatter);
    }

    public int getLineSize() {
        return lineSize;
    }
}
