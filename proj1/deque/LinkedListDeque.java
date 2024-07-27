package deque;

import java.util.Deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {

    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T t, Node p, Node n){
            item = t;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addLast(T item){
        sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size = size + 1;
    }

    public void addFirst(T item){
        sentinel.next.prev = new Node(item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size = size + 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = sentinel.next;

        while (p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (isEmpty()) return null;

        T FirstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size = size - 1;
        return FirstItem;
    }

    public T removeLast(){
        if (isEmpty()) return null;

        T LastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size = size - 1;
        return LastItem;
    }

    public T get(int index){
        if (index >= size()) return null;

        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index){
        if (index >= size()) return null;

        Node p = sentinel.next;
        if (index == 0) return p.item;
        return getRecursive(index - 1);
    }

    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node wizPos;

        public LinkedListDequeIterator(){
            wizPos = sentinel.next;
        }
        public boolean hasNext() {
            return wizPos != sentinel;
        }
        public T next() {
            T returnItem = wizPos.item;
            wizPos = wizPos.next;
            return returnItem;
        }
    }

    /*
    public boolean equals(Object o){

    }*/
}
