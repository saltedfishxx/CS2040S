///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

import java.util.Arrays;

/**
 * class ShiftRegister
 * @author Seow Xuan Yi
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    private final int size;
    private final int tap;
    private int[] arr;

    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        this.size = size;
        if(tap < size && tap > 0)
            this.tap = tap;
        else
            this.tap = 0;
        this.arr = new int[size-1];
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description: assign seed into Shift register object
     */
    @Override
    public void setSeed(int[] seed) {
        arr = seed;
        //if size = size of array, return error?
    }

    /**
     * shift
     * @return feedback
     * Description: Removes MSB and shift bits by 1, replacing LSB with XOR of MSB and tap
     */
    @Override
    public int shift() {
        //create feedback bit: XOR of most significant & tap bit
        int msb = arr[size-1];
        int tapV = arr[tap];
        int feedback = msb ^ tapV;
        //drop most significant bit & move bits to left by 1
        int[] temp = Arrays.copyOf(arr, arr.length-1);
        this.arr = Arrays.copyOf(temp, temp.length+1);
        //set feedback bit to LSB
        this.arr[0] = feedback;
        System.arraycopy(temp, 0, this.arr, 1, temp.length);

        return feedback;
    }

    /**
     * generate
     * @param k
     * @return decimal
     * Description: extract k bits in order after each shift operation, and returns the decimal form of the bits array
     */
    @Override
    public int generate(int k) {
        // iterate k times
        int[] temp = new int[k];
        for(int i = 0; i < k; i++) {
            temp[i] = shift();
        }

        return toDecimal(temp, k);
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array, size
     * @return result
     */
    private int toDecimal(int[] array, int size) {
        // computes 2^0 to 2^n size for 1 value bits and sums them into decimal
        int result = 0;
        for(int i = 0; i < size; i++) {
            if(array[i] == 1)
                result += (int) (Math.pow(2, size - i - 1) * array[i]);
        }
        return result;
    }

    /**
     * Returns the binary string representation for a text string password
     * @param password
     * @return seed
     */
    private String stringToBinary(String password) {
       StringBuilder seed = new StringBuilder();
       char[] passwordC = password.toCharArray();
       for (char c : passwordC) {
           seed.append(String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0"));
       }
       return seed.toString();
    }
}
