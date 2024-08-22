package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    protected String nameTag;
    private Map<String, String> attributes;

    public Tag(String nameTag, Map<String, String> attributes) {
        this.nameTag = nameTag;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return attributes.entrySet().stream()
                .map(el -> el.getKey() + "=\"" + el.getValue() + "\"")
                .collect(Collectors.joining(" ",
                        "<" + nameTag + (attributes.isEmpty() ? "" : " "),
                        ">"));
    }
}
// END
