import java.util.Arrays;

public class Queue {
    int[] qArr;

    public Queue(int size) {
       this.qArr = init(size);
    }

    private int[] init(int size) {
        int[] arr = new int[size];
        Arrays.fill(arr, Integer.MAX_VALUE);
        return arr;
    }

    public void enqueue(int element) {
        this.qArr[getVacantIndex()] = element;
    }

    public void dequeue() {
        int deq = this.qArr[0];
        int j = 0;
        for (int i = 0; i < this.qArr.length; i++) {
            if (this.qArr[i] != deq) {
                this.qArr[j++] = this.qArr[i];
            }
        }

        //remaining vacant indexes revert to default value
        while (j < this.qArr.length) {
            this.qArr[j++] = Integer.MAX_VALUE;
        }

        System.out.println(Arrays.toString(this.qArr));

    }


    public int peek() {
        return this.qArr[0];
    }

    public int getVacantIndex() {
        int result = this.qArr.length;
        for (int i = 0; i < this.qArr.length; i++) {
            if (this.qArr[i] == Integer.MAX_VALUE) {
                result = i;
                break;
            }
        }
        return result;
    }
}
