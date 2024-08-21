package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReversedSequenceTests {
    @Test
    void testReversedSequence() {
        CharSequence text = new ReversedSequence("abcdef");
        assertThat(text.toString()).isEqualTo("fedcba");
        assertThat(text.charAt(1)).isEqualTo('e');
        assertThat(text.length()).isEqualTo(6);
        assertThat(text.charAt(1)).isEqualTo('e');
        assertThat(text.subSequence(1, 4)).isEqualTo("edc");
    }
}
