package at.technikum.processor.util.strategy.impl;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.CoreUtil;
import at.technikum.processor.util.ParallelismUtil;
import at.technikum.processor.util.strategy.ImageStrategy;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class InvertStrategy implements ImageStrategy {

    @Override
    public void convertSerial(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {

                int rgb = image.getRGB(x, y);
                int gray = calculateInvertForPixel(rgb);

                image.setRGB(x, y, gray);
            }
        }
        ImageProcessor.getInstance().getImagePanel().replaceImage(image);
    }

    @Override
    public void convertParallel(BufferedImage image) {
        ParallelismUtil.parallelFor((i) -> {
            int chunkSize = image.getWidth() / CoreUtil.getNumberOfProcessors();
            IntStream.range(i * chunkSize, i * chunkSize + chunkSize).forEach(x -> {
                for (int y = 0; y < image.getHeight(); ++y) {

                    int rgb = image.getRGB(x, y);
                    int gray = calculateInvertForPixel(rgb);

                    image.setRGB(x, y, gray);
                }
            });
        });
        ImageProcessor.getInstance().getImagePanel().replaceImage(image);
    }

    private int calculateInvertForPixel(int rgb) {

        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb & 0xFF);

        int rr = 255 - r;
        int gg = 255 - g;
        int bb = 255 - b;

        return (rr << 16) | (gg << 8) | bb;
    }
}
