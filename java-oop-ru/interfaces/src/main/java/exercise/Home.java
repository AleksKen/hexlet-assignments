package exercise;

// BEGIN
public interface Home {
    double getArea();

    default int compareTo(Home another) {
        if (another.getArea() == getArea()) {
            return 0;
        }
        if (another.getArea() >= getArea()) {
            return -1;
        }
        return 1;
    }
}
// END
