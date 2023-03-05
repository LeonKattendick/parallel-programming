package at.technikum.exercise1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class DiningPhilosophers {

    @SneakyThrows
    public DiningPhilosophers(int n, int thinkingTime, int eatingTime) {

        Philosopher[] philosophers = new Philosopher[n];
        Thread[] philosopherThreads = new Thread[n];

        Object[] forks = new Object[n];

        for (int i = 0; i < n; i++) {
            forks[i] = new Object();
        }
        for (int i = 0; i < n; i++) {
            Philosopher philosopher = new Philosopher(forks[i], forks[(i + 1) % forks.length], thinkingTime, eatingTime);

            philosophers[i] = philosopher;
            philosopherThreads[i] = new Thread(philosopher, "philosopher-" + i);
            philosopherThreads[i].start();
        }

        Scanner s = new Scanner(System.in);
        s.nextLine();

        for (int i = 0; i < n; i++) {
            philosophers[i].stop();
        }
        for (int i = 0; i < n; i++) {
            philosopherThreads[i].join();
        }
        log.info("Finished program through input.");
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Start with: <Number of philosophers> <Thinking time> <Eating time>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int thinkingTime = Integer.parseInt(args[1]);
        int eatingTime = Integer.parseInt(args[2]);

        System.out.println("Starting with n = " + n + ", thinkingTime = " + thinkingTime + ", eatingTime = " + eatingTime);

        new DiningPhilosophers(n, thinkingTime, eatingTime);
    }
}
