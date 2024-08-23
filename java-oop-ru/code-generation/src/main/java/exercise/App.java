package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car car) {
        try {
            Files.write(path, car.serialize().getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path path) {
        try {
            return Car.deserialize(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
