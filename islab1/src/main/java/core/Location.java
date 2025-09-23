package core;

public class Location {
    private long x;
    private double y;
    private long z;

    public Location(long x, double y,long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}
