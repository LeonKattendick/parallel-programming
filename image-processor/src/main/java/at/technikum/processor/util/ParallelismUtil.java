package at.technikum.processor.util;

import at.technikum.processor.ImageProcessor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class ParallelismUtil {

    @SneakyThrows
    public static void parallelFor(int max, BiConsumer<Integer, Integer> consumer) {
        int procs = CoreUtil.getNumberOfProcessors();
        List<Future<?>> tasks = new ArrayList<>();

        IntStream.range(0, procs).forEach(i -> {
            Future<?> task = ImageProcessor.EXECUTOR_SERVICE.submit(() -> {

                int chunkSize = max / procs;
                int forEnd = i == procs - 1 ? max : i * chunkSize + chunkSize;

                consumer.accept(chunkSize * i, forEnd);
            });
            tasks.add(task);
        });

        for (Future<?> task : tasks) {
            task.get();
        }
    }
}
