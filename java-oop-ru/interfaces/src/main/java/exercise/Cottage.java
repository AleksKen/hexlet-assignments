package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floor;

    public Cottage(double area, int floor) {
        this.area = area;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return floor
                + " этажный коттедж площадью "
                + getArea()
                + " метров";
    }

    @Override
    public double getArea() {
        return area;
    }
}
// END
