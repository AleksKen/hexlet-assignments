package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        KeyValueStorage originalMap = new InMemoryKV(storage.toMap());
        KeyValueStorage temp = new InMemoryKV(Map.of());
        for (var entry : originalMap.toMap().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (storage.toMap().containsKey(value)) {
                temp.set(value, key);
            }
            else {
                storage.unset(key);
                storage.set(value, key);
            }
        }

        for (var entry : temp.toMap().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            storage.unset(key);
            storage.set(value, key);
        }
    }
}
// END
