package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    // Each Collection object is a single bucket, containing nodes.
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size = 16;
    private int num = 0;
    private double loadFactor = 0.75;
    private Set<K> k = new HashSet<>();

    /**
     * Constructors
     */
    public MyHashMap() {
        buckets = createTable(size);
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialSize) {
        size = initialSize;
        buckets = createTable(size);
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(size);
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        MyHashMap<K, V> temp = new MyHashMap(size, loadFactor);
        this.buckets = temp.buckets;
        this.num = temp.num;
        this.k = temp.k;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean containsKey(K key) {
        return k.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int hashCode = key.hashCode();
        int i = Math.floorMod(hashCode, size);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        return num;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        // double table size if average length of list >= 10
        if (num / size > loadFactor) {
            resize(2 * size);
        }
        int hashCode = key.hashCode();
        int i = Math.floorMod(hashCode, size);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        buckets[i].add(createNode(key, value));
        num += 1;
        k.add(key);
    }

    private void resize(int newSize) {
        MyHashMap<K, V> temp = new MyHashMap(newSize);
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                temp.put(node.key, node.value);
            }
        }
        this.buckets = temp.buckets;
        this.size = temp.size;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    public Set<K> keySet() {
        return k;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        int hashCode = key.hashCode();
        int i = Math.floorMod(hashCode, size);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                V removedValue = node.value;
                buckets[i].remove(node);
                num -= 1;
                k.remove(key);
                return removedValue;
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        int hashCode = key.hashCode();
        int i = Math.floorMod(hashCode, size);
        for (Node node : buckets[i]) {
            if (node.key.equals(key) && node.value.equals(value)) {
                V removedValue = node.value;
                buckets[i].remove(node);
                num -= 1;
                k.remove(key);
                return removedValue;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        Iterator<K> iterator;

        public HashMapIterator() {
            iterator = k.iterator();
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public K next() {
            return iterator.next();
        }
    }

}
