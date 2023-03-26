package at.technikum.processor.util;

public class CoreUtil {

    public static int getNumberOfProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }
}
