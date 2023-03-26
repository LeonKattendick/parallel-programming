package at.technikum.processor.util;

import at.technikum.processor.ImageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ParallelismUtil {

    public static void parallelFor(Consumer<Integer> consumer) {
        int procs = CoreUtil.getNumberOfProcessors();
        List<ForkJoinTask<?>> tasks = new ArrayList<>();

        IntStream.range(0, procs).forEach(i -> {
            ForkJoinTask<?> task = ImageProcessor.getForkJoinPool().submit(()-> consumer.accept(i));
            tasks.add(task);
        });

        for (ForkJoinTask<?> task : tasks) {
            task.join();
        }
    }
}
