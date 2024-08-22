package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String bodyTag;
    private List<Tag> childeTag;

    public PairedTag(String nameTag, Map<String, String> attributes, String bodyTag, List<Tag> childeTag) {
        super(nameTag, attributes);
        this.bodyTag = bodyTag;
        this.childeTag = childeTag;
    }

    @Override
    public String toString() {
        var str = super.toString() + bodyTag;
        var sub = childeTag.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
        return str + sub + "</" + super.nameTag + ">";
    }
}
// END
