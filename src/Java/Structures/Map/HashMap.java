package Java.Structures.Map;

import java.util.LinkedList;
import java.util.Objects;

public class HashMap<K, V> {

    private static class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final static int DEFAULT_CAPACITY = 16;
    private final static float LOAD_FACTOR = 0.75f;

    private LinkedList<Entry<K, V>>[] table;

    private int size = 0;
    private int capacity;

    public HashMap() {
        this.capacity = DEFAULT_CAPACITY;
        // Здесь нужно приведение, так как Java не позволяет создавать дженерик-массив напрямую
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
    }

    public HashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (Objects.equals(entry.key, key)) {
                entry.value = value;
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;

        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = hash(key);

        if (table[index] == null) return null;

        for (Entry<K, V> entry : table[index]) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public V remove(K key) {
        int index = hash(key);

        if (table[index] == null) return null;

        var iterator = table[index].iterator();

        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (Objects.equals(entry.key, key)) {
                V oldValue = entry.value;
                iterator.remove();
                size--;
                return oldValue;
            }
        }

        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        capacity *= 2;
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }
}
