import java.util.Random;

public class Guessing {

    // Your local variables here
    private int low = 0;
    private int high = 1000;
    private int guess;

    /**
     * Implement how your algorithm should make a guess here
     */
    public int guess() {
        //generates deterministic random number to send to server
        guess = (int) Math.floor(low + (high - low)/2);
        return guess;
    }

    /**
     * Implement how your algorithm should update its guess here
     */
    public void update(int answer) {
        //updates low/high value based on received answer
        if(answer == -1) {
            //if number is higher
            low = guess + 1;

        }else {
            //if number is lower
            high = guess;
        }
    }
}
