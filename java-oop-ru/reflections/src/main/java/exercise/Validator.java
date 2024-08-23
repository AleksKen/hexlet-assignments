package exercise;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        var fields = address.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> !checkNotNUll(field, address))
                .map(Field::getName)
                .toList();
    }

    private static boolean checkNotNUll(Field field, Address address) {
        if (field.isAnnotationPresent(NotNull.class)) {
            field.setAccessible(true);
            try {
                if (field.get(address) == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    private static boolean checkLength(Field field, Address address) {
        if (field.isAnnotationPresent(MinLength.class)) {
            field.setAccessible(true);
            try {
                if (field.get(address) != null) {
                    return field.getAnnotation(MinLength.class).minLength() < field.get(address).toString().length();
                }
                if (field.get(address) == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        var fields = address.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .map(field -> {
                    List<String> errors = new ArrayList<>();
                    if (!checkNotNUll(field, address)) {
                        errors.add("can not be null");
                    }
                    if (!checkLength(field, address)) {
                        errors.add("length less than 4");
                    }
                    return Map.entry(field.getName(), errors);
                })
                .filter(entry -> !entry.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
// END
