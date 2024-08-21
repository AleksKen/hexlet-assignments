package exercise;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String path;
    private Map<String, String> dictionary;

    public FileKV(Path path, Map<String, String> dictionary) {
        this.path = String.valueOf(path);
        this.dictionary = new HashMap<>(dictionary);
    }

    @Override
    public void set(String key, String value) {
        dictionary.putAll(Utils.deserialize(Utils.readFile(path)));
        dictionary.put(key, value);
        Utils.writeFile(path, Utils.serialize(dictionary));
    }

    @Override
    public void unset(String key) {
        dictionary.putAll(Utils.deserialize(Utils.readFile(path)));
        dictionary.remove(key);
        Utils.writeFile(path, Utils.serialize(dictionary));
    }

    @Override
    public String get(String key, String defaultValue) {
        dictionary.putAll(Utils.deserialize(Utils.readFile(path)));
        return  dictionary.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        dictionary.putAll(Utils.deserialize(Utils.readFile(path)));
        return new HashMap<>(dictionary);
    }
}
// END
