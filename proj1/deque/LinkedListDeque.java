package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T t, Node p, Node n) {
            item = t;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size = size + 1;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next.prev = new Node(item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size = size + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size = size - 1;
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size = size - 1;
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        Node p = sentinel.next;
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        LinkedListDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque that = (Deque) o;
        if (that.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); ++i) {
            if (that.get(i) != this.get(i)) {
                return false;
            }
        }
        return true;
    }
}
