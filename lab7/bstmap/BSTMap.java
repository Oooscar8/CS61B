package bstmap;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;        // root of BST

    private class BSTNode {
        K key;           // sorted by key
        V val;         // associated data
        int size;          // number of nodes in subtree
        BSTNode left, right;  // left and right subtrees

        BSTNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        return containsKey(root, key);
    }

    private boolean containsKey(BSTNode n, K key) {
        if (n == null) {
            return false;
        }
        if (n.key.compareTo(key) < 0) {
            return containsKey(n.right, key);
        }
        if (n.key.compareTo(key) > 0) {
            return containsKey(n.left, key);
        }
        return true;
    }

    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode n, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (n == null) {
            return null;
        }
        if (n.key.compareTo(key) < 0) {
            return get(n.right, key);
        }
        if (n.key.compareTo(key) > 0) {
            return get(n.left, key);
        }
        return n.val;
    }

    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size(root);
    }

    private int size(BSTNode n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode n, K key, V value) {
        if (n == null) {
            return new BSTNode(key, value, 1);
        }
        if (n.key.compareTo(key) < 0) {
            n.right = put(n.right, key, value);
        } else if (n.key.compareTo(key) > 0) {
            n.left = put(n.left, key, value);
        } else {
            n.val = value;
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BSTNode n) {
        if (n == null) {
            return;
        }
        printInOrder(n.left);
        System.out.print(n.val + " ");
        printInOrder(n.right);
    }

    @Override
    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        Set<K> k = new HashSet<>();
        for (K key : this) {
            k.add(key);
        }
        return k;
    }

    @Override
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        V value = get(key);
        if (value != null) {
            root = remove(root, key);
            return value;
        }
        return null;
    }

    private BSTNode remove(BSTNode n, K key) {
        if (n == null) return null;
        if (n.key.compareTo(key) < 0) {
            n.right = remove(n.right, key);
        } else if (n.key.compareTo(key) > 0) {
            n.left = remove(n.left, key);
        } else {
            if (n.right == null) {
                return n.left;
            }
            if (n.left == null) {
                return n.right;
            }
            BSTNode t = min(n.right);
            n.right = deleteMin(n.right);
            return t;
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    private BSTNode min(BSTNode n) {
        if (n.left == null) {
            return n;
        }
        return min(n.left);
    }

    private BSTNode deleteMin(BSTNode n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = deleteMin(n.left);
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    @Override
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        if (value == get(key)) {
            root = remove(root, key);
            return value;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        BSTNode current;
        private Stack<BSTNode> BSTStack = new Stack<>();

        public BSTIterator(BSTNode n) {
            current = n;
        }

        public boolean hasNext() {
            return (!BSTStack.isEmpty() || current != null);
        }

        public K next() {
            while (current != null) {
                BSTStack.push(current);
                current = current.left;
            }

            current = BSTStack.pop();
            BSTNode node = current;
            current = current.right;
            return node.key;
        }
    }
}
