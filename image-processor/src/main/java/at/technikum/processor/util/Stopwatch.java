package at.technikum.processor.util;

public class Stopwatch {

    private final long startTime;

    public Stopwatch() {
        this.startTime = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - this.startTime;
    }
}