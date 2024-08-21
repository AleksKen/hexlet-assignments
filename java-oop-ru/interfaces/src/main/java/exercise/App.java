package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int n) {
        var res =  homes.stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .limit(n)
                .collect(Collectors.toList());
        System.out.println(res);
        return res;
    }
}
// END
