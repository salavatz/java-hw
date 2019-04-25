package part01.lesson04.task02;

import java.util.Collection;

public class ObjectBox {
    private Collection<Object> collection;

    public ObjectBox(Collection<Object> collection) {
        this.collection = collection;
    }

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
