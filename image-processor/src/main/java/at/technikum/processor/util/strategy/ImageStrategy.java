package at.technikum.processor.util.strategy;

import java.awt.image.BufferedImage;

public interface ImageStrategy {

    void convertSerial(BufferedImage image);

    void convertParallel(BufferedImage image);

}
