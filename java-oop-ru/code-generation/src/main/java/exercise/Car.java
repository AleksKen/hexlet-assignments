package exercise;

import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;


// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize()  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    @SneakyThrows
    public static Car deserialize(String carJson)  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(carJson, Car.class);
    }
    // END
}
