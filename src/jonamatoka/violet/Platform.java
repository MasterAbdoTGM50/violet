package jonamatoka.violet;

import org.dizitart.no2.Nitrite;

public class Platform {

    public Nitrite db;

    private static Platform instance;

    private Platform() { db = Nitrite.builder().openOrCreate(); }

    public void persist() { /* Dummy */ }

    public void commit() { db.commit(); }

    public static Platform get() {

        if (instance == null) { instance = new Platform(); }
        return instance;

    }

}
