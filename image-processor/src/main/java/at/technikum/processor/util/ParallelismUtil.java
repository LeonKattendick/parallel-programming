package at.technikum.processor.util;

import at.technikum.processor.ImageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class ParallelismUtil {

    public static void parallelFor(int max, BiConsumer<Integer, Integer> consumer) {
        int procs = CoreUtil.getNumberOfProcessors();
        List<ForkJoinTask<?>> tasks = new ArrayList<>();

        IntStream.range(0, procs).forEach(i -> {
            ForkJoinTask<?> task = ImageProcessor.getForkJoinPool().submit(() -> {

                int chunkSize = max / procs;
                int forEnd = i == procs - 1 ? max : i * chunkSize + chunkSize;

                consumer.accept(chunkSize * i, forEnd);
            });
            tasks.add(task);
        });

        for (ForkJoinTask<?> task : tasks) {
            task.join();
        }
    }
}
