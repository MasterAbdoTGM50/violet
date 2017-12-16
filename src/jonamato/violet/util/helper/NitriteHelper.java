package jonamato.violet.util.helper;

import jonamato.violet.Lib;
import org.dizitart.no2.Document;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.List;

public class NitriteHelper {

    @SuppressWarnings("unchecked")
    public static <T> boolean insert(T obj, Class<T> clazz) {

        try {

            ObjectRepository<T> objs = Lib.Platform.db.getRepository(clazz);
            objs.insert(obj);

        } catch (UniqueConstraintException e) { return false; }

        return true;

    }

    public static <T> boolean update(T obj, Class<T> clazz) {

        ObjectRepository<T> objs = Lib.Platform.db.getRepository(clazz);
        objs.update(obj);
        return true;

    }

    public static <T> boolean remove(T obj, Class<T> clazz) {

        ObjectRepository<T> objs = Lib.Platform.db.getRepository(clazz);
        objs.remove(obj);
        return true;

    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> all(Class<T> clazz) {

        List<T> all = new ArrayList<>();

        ObjectRepository<T> objs = Lib.Platform.db.getRepository(clazz);
        for(Document doc: objs.getDocumentCollection().find()) {

            try {

                all.add(discriminate(doc, (doc.get("type") == null ? clazz : (Class<T>)Class.forName((String)doc.get("type")))));

            } catch (ClassNotFoundException e) { e.printStackTrace(); }

        }

        return all;

    }

    private static <T> T discriminate(Document doc, Class<T> clazz) {

        return Lib.Platform.db.getContext().getNitriteMapper().asObject(doc, clazz);

    }

}
