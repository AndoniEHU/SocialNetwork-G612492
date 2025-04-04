package incl;

import incl.exceptions.EmptyCollectionException;
import incl.exceptions.NonComparableElementException;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (element instanceof Comparable) {
            temp = (Comparable)element;
        }
        else {
            throw new NonComparableElementException("ordered list");
        }
        Node<T> traverse = head;
        Node<T> newNode  = new Node<T>(element);
        if (isEmpty())
        {
            head = newNode;
            last = newNode;
        }
        else if(temp.compareTo(last.getElement()) >= 0)
        {
            last.setNext(newNode);
            last = newNode;
        }
        else if (temp.compareTo(head.getElement()) <= 0)
        {
            newNode.setNext(traverse);
            head = newNode;
        }
        else
        {
            Node<T> previous = traverse;
            traverse = traverse.getNext();
            while ((temp.compareTo(traverse.getElement()) > 0)) {
                previous = traverse;
                traverse = traverse.getNext();
            }
            newNode.setNext(traverse);
            previous.setNext(newNode);

        }
        count++;
    }
    @Override
    public T removeFirst() {
        if (isEmpty())
            throw new EmptyCollectionException("list is empty");
        if(count==1) {
            head = null;
            last = head;
        }
        else {
            head = head.getNext();
        }
        count--;
        return head.getElement();
    }
    @Override
    public T removeLast() {
        if (isEmpty())
            throw new EmptyCollectionException("list is empty");
        Node<T>traverse=head;
        if(count==1) {
        	last = head;
        }
        else {
        	while(!traverse.getNext().getElement().equals(last.getElement())) {
                traverse = traverse.getNext();
            }
            traverse.setNext(null);
            last=traverse;
        }
        count--;
        return last.getElement();
    }
    @Override
    public T remove(T element) {

        if (isEmpty()) {
            throw new EmptyCollectionException("list is empty");
        }

        if (contains(element)) {
            if(head.getElement().equals(element)) {
                return removeFirst();
            }
            
            if(head.getElement().equals(element)) {
            	return removeLast();
            }
            else {
                Node<T>traverse=head;
                while(!traverse.getNext().getElement().equals(element)) {
                    traverse = traverse.getNext();
                }

                traverse.setNext(traverse.getNext().getNext());
                count--;
                return traverse.getElement();
            }
        }
        else {
            throw new NoSuchElementException("element not in the list");
        }
    }
    @Override
    public T first() {
        return head.getElement();
    }
    @Override
    public T last() {
        return last.getElement();
    }
    @Override
    public boolean contains(T target) {
        if (isEmpty()) {
            return false;
        }
        Node<T>traverse=head;
        while(!traverse.getElement().equals(target)) {
            if (traverse.getNext()==null) {
                return false;
            }
            traverse = traverse.getNext();
        }
        return true;
    }
    @Override
    public boolean isEmpty() {
        if(count==0) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public int size() {
        return count;
    }
    @Override
    public Iterator<T> iterator() {
        return new SingleIterator<>(head,count);
    }

    public void showOrderedList() { 
    	Node<T> traverse = head;
    	for (int i=0; i<size(); i++) {
    		System.out.print(" -> " + traverse.getElement());
    		traverse=traverse.getNext();
    	}
    }
}
