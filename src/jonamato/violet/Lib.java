package jonamato.violet;

import jonamato.violet.account.User;
import org.dizitart.no2.Nitrite;

public class Lib {

    public static class Platform {

        public static User user = null;
        public static Nitrite db = Nitrite.builder().openOrCreate();

    }

    public static class Paths {

        public static final String DB_PATH = "db/violet.nitdb";

    }

}
