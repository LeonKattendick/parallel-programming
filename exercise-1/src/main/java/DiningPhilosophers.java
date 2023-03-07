import java.util.Random;
import java.util.Scanner;

public class DiningPhilosophers {

    public DiningPhilosophers(int n, int thinkingTime, int eatingTime) {

        Philosopher[] philosophers = new Philosopher[n];
        Thread[] philosopherThreads = new Thread[n];

        Object[] forks = new Object[n];

        for (int i = 0; i < n; i++) {
            forks[i] = new Object();
        }

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();

        for (int i = 0; i < n; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];
            boolean isEven = i % 2 == 0;

            Philosopher philosopher = new Philosopher(isEven ? rightFork : leftFork, isEven ? leftFork : rightFork, thinkingTime, eatingTime);

            philosophers[i] = philosopher;
            philosopherThreads[i] = new Thread(philosopher, "philosopher-" + i);
            philosopherThreads[i].start();
        }

        Scanner s = new Scanner(System.in);
        s.nextLine();


        for (int i = 0; i < n; i++) {
            philosophers[i].stop();
        }
        double totalWaitedTimeAllPhilosophers = 0;
        for (int i = 0; i < n; i++) {
            try {
                philosopherThreads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            totalWaitedTimeAllPhilosophers += philosophers[i].getTotalWaitedTime();
        }

        double totalRuntime = stopwatch.stop();

        String log = String.format("Total Runtime: %.0fms | Total Time spent waiting: %.0fms | Percentage: %.2f%%",
                totalRuntime,
                totalWaitedTimeAllPhilosophers,
                (totalWaitedTimeAllPhilosophers / totalRuntime) * 100
        );

        System.out.println("Finished program through input.");
        System.out.println(log);
    }

    static class Philosopher implements Runnable {

        private final Object fork1;

        private final Object fork2;

        private final int thinkingTime;

        private final int eatingTime;

        private boolean stop;

        private long totalWaitedTime;

        Philosopher(Object fork1, Object fork2, int thinkingTime, int eatingTime) {
            this.fork1 = fork1;
            this.fork2 = fork2;
            this.thinkingTime = thinkingTime;
            this.eatingTime = eatingTime;
        }

        @Override
        public void run() {

            Stopwatch stopwatch = new Stopwatch();

            while (!stop) {
                sleepRandom(thinkingTime);
                System.out.println("[" + Thread.currentThread().getName() + "] Finished thinking");

                stopwatch.start();
                synchronized (fork1) {

                    System.out.println("[" + Thread.currentThread().getName() + "] Picked up first fork");
                    synchronized (fork2) {
                        totalWaitedTime += stopwatch.stop();
                        System.out.println("[" + Thread.currentThread().getName() + "] Picked up second fork");
                        sleepRandom(eatingTime);
                        System.out.println("[" + Thread.currentThread().getName() + "] Finished eating");
                    }
                    System.out.println("[" + Thread.currentThread().getName() + "] Put down second fork");
                }
                System.out.println("[" + Thread.currentThread().getName() + "] Put down first fork");
            }
        }

        private void sleepRandom(int maxTime) {
            try {
                Thread.sleep(new Random().nextInt(maxTime));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void stop() {
            this.stop = true;
        }

        public long getTotalWaitedTime() {
            return totalWaitedTime;
        }
    }

    static class Stopwatch {

        private long startTime;

        public void start() {
            this.startTime = System.currentTimeMillis();
        }

        public long stop() {
            return System.currentTimeMillis() - this.startTime;
        }
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
