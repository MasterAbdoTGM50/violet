package jonamato.violet.account;

import jonamato.violet.Lib;
import jonamato.violet.util.helper.NitriteHelper;
import net.openhft.hashing.LongHashFunction;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

public class Registry {

    private static Registry instance = new Registry();

    private Registry() { }

    public static Registry instance() { return instance; }

    public boolean register(User user, String pass) {

        user.setHash(LongHashFunction.xx().hashChars(pass));
        return NitriteHelper.insert(user, User.class);

    }

    public User login(String username, String pass) {

        return all().stream()
                .filter(u -> u.getUsername().equals(username) && u.getHash() == LongHashFunction.xx().hashChars(pass))
                .findFirst()
                .orElse(null);

    }

    public boolean update(User user) {  return NitriteHelper.update(user, User.class); }

    public boolean remove(User user) {

        ObjectRepository<User> users = Lib.Platform.db.getRepository(User.class);
        users.remove(user);
        return true;

    }

    public List<User> all() { return NitriteHelper.all(User.class); }

}
