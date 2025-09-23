import core.Dragon;
import storage.DatabaseProcessor;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DatabaseProcessor dp = new DatabaseProcessor();
        ArrayList<Dragon> array = dp.getDragons();

        for (Dragon dragon : array) {
            System.out.println(dragon.toString());
        }
    }
}
