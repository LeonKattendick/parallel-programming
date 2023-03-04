package at.technikum.exercise1;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class Philosopher implements Runnable {

    private final Object forkLeft;
    private final Object forkRight;

    private final int thinkingTime;
    private final int eatingTime;

    private volatile boolean stop;

    @Override
    public void run() {
        while (!stop) {
            sleepRandom(thinkingTime);
            log.info("Finished thinking");

            synchronized (forkLeft) {
                log.info("Picked up left fork");
                synchronized (forkRight) {
                    log.info("Picked up right fork");
                    sleepRandom(eatingTime);
                    log.info("Finished eating");
                }
                log.info("Put down right fork");
            }
            log.info("Put down left fork");
        }
    }

    @SneakyThrows
    private void sleepRandom(int maxTime) {
        Thread.sleep(new Random().nextInt(maxTime));
    }

    public void stop() {
        this.stop = true;
    }
}
