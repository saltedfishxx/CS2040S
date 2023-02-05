public class Watch {
    private long startTime = 0;
    private long endTime = 0;
    private long duration = 0;

    Watch() {
        reset();
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
        duration = 0;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
        duration += (endTime - startTime);
    }

    public float getTime() {
        float r = duration;
        r /= 1000000000;
        return r;
    }
}
