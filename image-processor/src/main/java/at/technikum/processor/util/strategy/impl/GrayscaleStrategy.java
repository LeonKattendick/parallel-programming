package at.technikum.processor.util.strategy.impl;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.ParallelismUtil;
import at.technikum.processor.util.strategy.ImageStrategy;

import java.awt.image.BufferedImage;

public class GrayscaleStrategy implements ImageStrategy {

    @Override
    public void convertSerial(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {

                int rgb = image.getRGB(x, y);
                int gray = calculateGrayForPixel(rgb);

                image.setRGB(x, y, gray);
            }
        }
        ImageProcessor.getInstance().getImagePanel().replaceImage(image);
    }

    @Override
    public void convertParallel(BufferedImage image) {
        ParallelismUtil.parallelFor(image.getWidth(), (start, end) -> {
            for (int x = start; x < end; x++) {
                for (int y = 0; y < image.getHeight(); ++y) {

                    int rgb = image.getRGB(x, y);
                    int gray = calculateGrayForPixel(rgb);

                    image.setRGB(x, y, gray);
                }
            }
        });
        ImageProcessor.getInstance().getImagePanel().replaceImage(image);
    }

    private int calculateGrayForPixel(int rgb) {

        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb & 0xFF);

        float rr = (float) Math.pow(r / 255.0, 2.2);
        float gg = (float) Math.pow(g / 255.0, 2.2);
        float bb = (float) Math.pow(b / 255.0, 2.2);

        float lum = (float) (0.2126 * rr + 0.7152 * gg + 0.0722 * bb);
        int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));

        return (grayLevel << 16) + (grayLevel << 8) + grayLevel;
    }
}
