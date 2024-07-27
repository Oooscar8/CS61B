package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c){
        comparator = c;
    }

    public T max(){
        T currentMax = this.get(0);
        for (int i = 1; i < this.size(); i++){
            if (comparator.compare(this.get(i), currentMax) > 0){
                currentMax = this.get(i);
            }
        }
        return currentMax;
    }

    public T max(Comparator<T> c){
        comparator = c;
        T currentMax = this.get(0);
        for (int i = 1; i < this.size(); i++){
            if (comparator.compare(this.get(i), currentMax) > 0){
                currentMax = this.get(i);
            }
        }
        return currentMax;
    }
}
