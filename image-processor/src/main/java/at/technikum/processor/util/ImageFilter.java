package at.technikum.processor.util;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.stream.Stream;

public class ImageFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory()|| Stream.of(".jpg", ".jpeg", ".png").anyMatch(v -> f.getName().contains(v));
    }

    @Override
    public String getDescription() {
        return "Nur Bilder (.jpg, .jpeg, .png)";
    }
}
