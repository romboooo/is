package core;

public class DragonHead {
    private Float toothCount; //Поле может быть null
    public DragonHead(Float toothCount) {
        this.toothCount = toothCount;
    }

    @Override
    public String toString() {
        return "DragonHead [toothCount=" + toothCount + "]";
    }
}

