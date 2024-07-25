package deque;

public class ArrayDeque<T> {
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

    /**
    private void resize(int capacity) {

    }
     */

    public void addFirst(T item){
        items[nextFirst] = item;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size = size + 1;
    }

    public void addLast(T item){
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

        T FirstItem = items[(nextFirst + 1) % items.length];
        nextFirst = (nextFirst + 1) % items.length;
        size = size - 1;
        return FirstItem;
    }

    public T removeLast(){
        if (isEmpty()) return null;

        T LastItem = items[(nextLast + items.length - 1) % items.length];
        nextLast = (nextLast + items.length - 1) % items.length;
        size = size - 1;
        return LastItem;
    }

    public T get(int index) {
        if (nextFirst < nextLast) {
            if (index > nextFirst && index < nextLast) {
                return items[index];
            }
            else return null;
        }
        if (index > nextLast && index < nextFirst) {
            return null;
        }
        return items[index];
    }

    /**
    public Iterator<T> iterator(){

     }

    public boolean equals(Object o){

     }
     */
}
