package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }


    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        int j = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; ++i) {
            a[i] = items[j];
            j = (j + 1) % items.length;
        }

        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }


    public void addFirst(T item){
        if (size == items.length){
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size = size + 1;
    }

    public void addLast(T item){
        if (size == items.length){
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size = size + 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }
        else {
            for (int i = nextFirst + 1; i < nextLast + items.length; i--) {
                System.out.print(items[i % items.length] + " ");
            }
            System.out.println();
        }
    }

    public T removeFirst(){
        if (isEmpty()) return null;

        if (items.length >= 16 && size - 1 < items.length / 4) {
            resize(items.length / 2);
        }

        T FirstItem = items[(nextFirst + 1) % items.length];
        nextFirst = (nextFirst + 1) % items.length;
        size = size - 1;
        return FirstItem;
    }

    public T removeLast(){
        if (isEmpty()) return null;

        if (items.length >= 16 && size - 1 < items.length / 4) {
            resize(items.length / 2);
        }

        T LastItem = items[(nextLast + items.length - 1) % items.length];
        nextLast = (nextLast + items.length - 1) % items.length;
        size = size - 1;
        return LastItem;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
     }

     private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        private int i;

        public ArrayDequeIterator(){
            wizPos = (nextFirst + 1) % items.length;
            i = 0;
        }
        public boolean hasNext() {
            return i < size;
        }
        public T next() {
            T returnItem = items[wizPos];
            wizPos = (wizPos + 1) % items.length;
            i++;
            return returnItem;
        }
     }

     /*
    public boolean equals(Object o){

     }
     */
}
