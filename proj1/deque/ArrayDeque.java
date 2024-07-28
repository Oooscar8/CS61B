package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
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

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length){
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
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

        if (items.length >= 16 && size - 1 < items.length / 4) {
            resize(items.length / 2);
        }

        T firstItem = items[(nextFirst + 1) % items.length];
        nextFirst = (nextFirst + 1) % items.length;
        size = size - 1;
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (items.length >= 16 && size - 1 < items.length / 4) {
            resize(items.length / 2);
        }

        T lastItem = items[(nextLast + items.length - 1) % items.length];
        nextLast = (nextLast + items.length - 1) % items.length;
        size = size - 1;
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
     }

     private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDequeIterator() {
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
        if (this == o){
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
