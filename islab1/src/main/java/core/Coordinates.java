package core;

public class Coordinates {
    private int x;
    private Long y; //Поле не может быть null

    public Coordinates(int x, Long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Coordinates{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append("}");
        return sb.toString();
    }
}
