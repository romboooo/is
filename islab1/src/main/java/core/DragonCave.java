package core;

public class DragonCave {
    private int numberOfTreasures; //Значение поля должно быть больше 0

    public DragonCave(int numberOfTreasures) {
        this.numberOfTreasures = numberOfTreasures;
    }

    @Override
    public String toString() {
        return "DragonCave [number of treasures=" + numberOfTreasures + "]";
    }
}