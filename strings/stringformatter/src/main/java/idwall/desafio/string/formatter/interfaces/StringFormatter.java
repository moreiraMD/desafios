package idwall.desafio.string.formatter.interfaces;

@FunctionalInterface
public interface StringFormatter {

    String format(String text);

    default StringFormatter otherFormat(StringFormatter formatter) {
        return text -> formatter.format(this.format(text));
    }
}
