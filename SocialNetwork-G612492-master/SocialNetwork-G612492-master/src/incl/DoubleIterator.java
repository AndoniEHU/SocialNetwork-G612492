package incl;

/**
 * DoubleIterator represents an iterator for a doubly linked list of nodes.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

import java.util.*;

public class DoubleIterator<T> implements Iterator<T>
{
    private int count;  // the number of elements in the collection
    private DoubleNode<T> current;  // the current position

    /**
     * Sets up this iterator using the specified items.
     *
     * @param list  the list that the iterator is to be created for
     * @param size  the integer value for the size of the list
     */
    public DoubleIterator (DoubleNode<T> list, int size)
    {
        current = list;
        count = size;
    }

    /**
     * Returns true if this iterator has at least one more element
     * to deliver in the iteration.
     *
     * @return  true if this iterator has at least one more element to deliver
     *          in the interation
     */
    public boolean hasNext()
    {
        return (current != null);
    }

    /**
     * Returns the next element in the iteration. If there are no
     * more elements in this iteration, a NoSuchElementException is
     * thrown.
     *
     * @return                         the next element in the iteration
     * @throws NoSuchElementException  if an element not found exception occurs
     */
    public T next()
    {
        if (! hasNext())
            throw new NoSuchElementException();

        T result = current.getElement();
        current = current.getNext();
        return result;
    }

    /**
     * The remove operation is not supported.
     *
     * @throws UnsupportedOperationException  if an unsupported operation
     *                                        exception occurs
     */
    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
}

