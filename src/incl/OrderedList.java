package incl;

import incl.exceptions.EmptyCollectionException;
import incl.exceptions.NonComparableElementException;

import java.util.Iterator;

public class OrderedList<T> implements ListADT<T>, OrderedListADT<T> {

    private Node<T> head;
    private Node<T> last;
    private int count;

    public OrderedList() {
        this.head = null;
        this.count = 0;
    }

    @Override
    public void add(T element) {

        Comparable temp;
        if (element instanceof Comparable)
            temp = (Comparable)element;
        else
            throw new NonComparableElementException("ordered list");

        Node<T> traverse = head;
        Node<T> newNode  = new Node<T>(element);

        if (isEmpty())
        {
            head = newNode;
        }

        else if (temp.compareTo(head.getElement()) <= 0)
        {
            newNode.setNext(traverse);
            head = newNode;
        }
        else
        {
            while ((temp.compareTo(traverse.getElement()) >= 0))
                traverse = traverse.getNext();

            newNode.setNext(traverse.getNext());
            traverse.setNext(newNode);
        }

        count++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty())
            throw new EmptyCollectionException("list is empty");
        Node<T> temp = head;
        head = head.getNext();
        count--;
        return head.getElement();
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public T first() {
        return null;
    }

    @Override
    public T last() {
        return null;
    }

    @Override
    public boolean contains(T target) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
