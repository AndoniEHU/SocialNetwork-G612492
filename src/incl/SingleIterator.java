package incl;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleIterator<T> implements Iterator<T> {

    private int count;
    private Node<T> current;

    public SingleIterator(Node<T> root,int size) {
        current = root;
        count = size;
    }

    @Override
    public boolean hasNext() {
        return current !=null;
    }

    @Override
    public T next() {
        if (! hasNext())
            throw new NoSuchElementException();

        T result = current.getElement();
        current = current.getNext();
        return result;
    }

    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
}
