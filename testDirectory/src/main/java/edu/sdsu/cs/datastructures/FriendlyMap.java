package edu.sdsu.cs.datastructures;

import java.util.Map;

/**
 * This class extends the comparable class and implements the interface
 * provided by the professor.
 *
 * Use unit tests in the test folder to test each change and implementation
 * we make to the code. The professor will run his own unit tests to the
 * assignment.
 *
 *
 * @param <K>
 * @param <V>
 */

public class FriendlyMap<K extends Comparable<K>,V> implements IMap<K,V>{

    /**
     * MapNode class.
     */
    private class MapNode implements Comparable<MapNode>{
        MapEntry contents;
        MapNode left;
        MapNode right;


        @Override
        public int compareTo(MapNode mapNode) {
            return contents.compareTo(mapNode.contents);
        }
    }

    /**
     * MapEntry class
     */
    private class MapEntry implements Comparable<MapEntry> {
        K key;
        V value;

        @Override
        public int compareTo(MapEntry mapEntry) {
            return key.compareTo(mapEntry.key);
        }
    }

    MapNode root;

    @Override
    public boolean contains(Comparable key) {
        return false;
    }

    @Override
    public boolean add(Comparable key, Object value) {
        return false;
    }

    @Override
    public Object delete(Comparable key) {
        return null;
    }

    @Override
    public Object getValue(Comparable key) {
        return null;
    }

    @Override
    public Comparable getKey(Object value) {
        return null;
    }

    @Override
    public Iterable getKeys(Object value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterable keyset() {
        return null;
    }

    @Override
    public Iterable values() {
        return null;
    }
}
