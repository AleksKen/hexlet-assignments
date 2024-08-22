package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String data;
    private TagInterface tag;

    public LabelTag(String data, TagInterface tag) {
        this.data = data;
        this.tag = tag;
    }

    @Override
    public String render() {
        return "<label>" + data + tag.render() + "</label>";
    }
}
// END
