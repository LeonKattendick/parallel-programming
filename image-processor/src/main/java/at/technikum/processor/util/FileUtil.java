package at.technikum.processor.util;

import java.io.File;

public class FileUtil {

    public static String getImageTypeFromFile(File file) {
        if (file.getName().endsWith(".jpg")) return "jpg";
        if (file.getName().endsWith(".jpeg")) return "jpeg";

        return "png";
    }
}
