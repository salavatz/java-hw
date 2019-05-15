package part01.lesson04.task03;

import java.util.HashSet;
import java.util.Set;

public class ObjectBox {
    protected Set<Object> collection = new HashSet<>();

    public void add(Object o) {
        collection.add(o);
    }

    public void delete(Object o) {
        collection.remove(o);
    }

    public String dump() {
       return collection.toString();
    }
}
