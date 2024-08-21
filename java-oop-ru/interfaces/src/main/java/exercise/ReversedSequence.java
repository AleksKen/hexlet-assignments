package exercise;

import java.util.stream.IntStream;

// BEGIN
public class ReversedSequence implements CharSequence {
    private static String text;

    public ReversedSequence(String text) {
        this.text = reversed(text);
    }

    @Override
    public char charAt(int index) {
        return text.charAt(index);
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return text.substring(start, end);
    }

    private static String reversed(String text) {
        return IntStream.range(1, text.length() + 1)
                .boxed()
                .map(el -> String.valueOf(text.charAt(text.length() - el)))
                .reduce("", (acc, el) -> acc + el);
    }
}
// END
