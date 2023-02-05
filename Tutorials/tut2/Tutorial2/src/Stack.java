import java.util.Arrays;

public class Stack {
    int[] stackArr;
    public Stack (int size) {
        this.stackArr = init(size);
    }

    private int[] init(int size) {
        int[] arr = new int[size];
        Arrays.fill(arr, Integer.MAX_VALUE);
        return arr;
    }
    public void push(int element) {
        this.stackArr[getVacantIndex()] = element;
    }

    public void pop() {
        this.stackArr[getVacantIndex()-1] = Integer.MAX_VALUE;
    }

    public int peek() {
        return this.stackArr[getVacantIndex()-1];
    }

    public int getVacantIndex() {
        int result = this.stackArr.length;
        for (int i = 0; i < this.stackArr.length; i++) {
            if (this.stackArr[i] == Integer.MAX_VALUE) {
                result = i;
                break;
            }
        }
        return result;
    }
}
