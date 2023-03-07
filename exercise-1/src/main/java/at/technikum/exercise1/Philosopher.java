package at.technikum.exercise1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class Philosopher implements Runnable {

    private final Object fork1;

    private final Object fork2;

    private final int thinkingTime;

    private final int eatingTime;

    private boolean stop;

    @Getter
    private long totalWaitedTime;

    @Override
    public void run() {

        Stopwatch stopwatch = new Stopwatch();

        while (!stop) {
            sleepRandom(thinkingTime);
            log.info("Finished thinking");

            stopwatch.start();
            synchronized (fork1) {

                log.info("Picked up first fork");
                synchronized (fork2) {
                    totalWaitedTime += stopwatch.stop();
                    log.info("Picked up second fork");
                    sleepRandom(eatingTime);
                    log.info("Finished eating");
                }
                log.info("Put down second fork");
            }
            log.info("Put down first fork");
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
