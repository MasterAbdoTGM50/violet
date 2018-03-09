package jonamatoka.violet.util;

import jonamatoka.violet.Platform;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

public class NitriteHelper {

    private static NitriteHelper instance;

    private NitriteHelper() { }
    public static NitriteHelper get() {

        if(null == instance) { instance = new NitriteHelper(); }
        return instance;

    }

    @SuppressWarnings("unchecked")
    public <T> boolean insert(T obj, Class<T> clazz) {

        try {

            ObjectRepository<T> objs = Platform.get().db.getRepository(clazz);
            objs.insert(obj);

        } catch (UniqueConstraintException e) { return false; }

        return true;

    }

    public <T> boolean update(T obj, Class<T> clazz) {

        ObjectRepository<T> objs = Platform.get().db.getRepository(clazz);
        objs.update(obj);
        return true;

    }

    public <T> boolean remove(T obj, Class<T> clazz) {

        ObjectRepository<T> objs = Platform.get().db.getRepository(clazz);
        objs.remove(obj);
        return true;

    }

    public <T> List<T> all(Class<T> clazz) {

        ObjectRepository<T> objs = Platform.get().db.getRepository(clazz);
        return objs.find().toList();

    }

}
