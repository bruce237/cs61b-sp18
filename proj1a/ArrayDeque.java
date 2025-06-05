/**
 *usage ratio issue is still unsolved
 * invariants : front items[front]是数组的首元素 尾元素是items[size-1]
 * @param <horse>
 */
public class ArrayDeque<horse> {
    private horse[] items;
    private int front;
    private int size;

    public ArrayDeque(){
        items = (horse[]) new Object[8];
        front = 0;
        size = 0;
    }

    public int minusOne(int index){
        if(index == 0){
            return 0;
        }
        else{
            return index-1;
        }
    }
    public void addFirst(horse item){
        int index = minusOne(front);
        items[index] = item;
        front = index;
        size ++;
    }

    private void resize(int capacity){
        horse[] a = (horse[]) new Object[capacity];
        items = a;
    }
    public void addLast(horse item){
        if(size == items.length){
            resize(size * 2);
        }
        items[size] = item;
        size ++;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for (int i = front; i < size+front; i++) {
            System.out.printf(items[i] + " ");
        }
    }

    public horse removeFirst(){
        horse a = items[front];
        items[front] = null;
        size--;
        front++;
        return a;
    }

    public horse removeLast(){
        horse a = items[size - 1];
        items[size - 1] = null;
        size--;
        return a;
    }
    public horse get(int index){
        if(index < 0 || index > size - 1){
            return null;
        }
        return items[index];
    }
}
