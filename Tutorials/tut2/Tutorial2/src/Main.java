public class Main {
    public static void main(String[] args) {
        Stack stk = new Stack(4);
        stk.push(1);
        stk.push(2);
        stk.push(3);
        stk.push(4);
        System.out.println(stk.peek());
        stk.pop();
        System.out.println(stk.peek());

        Queue q = new Queue(4);
        q.enqueue(1);
        q.enqueue(2);
        System.out.println(q.peek());
        q.enqueue(3);
        q.enqueue(4);
        q.dequeue();
        System.out.println(q.peek());


    }


}