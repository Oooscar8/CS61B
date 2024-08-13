package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

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
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    public MyHashMap(int initialSize) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return null;
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
        return null;
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
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean containsKey(K key) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    public Set<K> keySet() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {

        public HashMapIterator() {
            throw new UnsupportedOperationException("This operation is not supported");
        }

        public boolean hasNext() {
            throw new UnsupportedOperationException("This operation is not supported");
        }

        public K next() {
            throw new UnsupportedOperationException("This operation is not supported");
        }
    }

}
