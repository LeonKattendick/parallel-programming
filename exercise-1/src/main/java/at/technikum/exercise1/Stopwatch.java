package at.technikum.exercise1;

public class Stopwatch {

    private long startTime;

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - this.startTime;
    }
}
