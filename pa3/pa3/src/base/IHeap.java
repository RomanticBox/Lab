public interface IHeap<T extends Comparable> {
    T min();
    T removeMin();
    void insert(T entry);
    void clear();
    int getSize();
    boolean isEmpty();
    void merge(Heap<T> otherHeap);
}
