package at.technikum.processor.util.strategy.impl;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.ParallelismUtil;
import at.technikum.processor.util.strategy.ImageStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HueStrategy implements ImageStrategy {

    @Override
    public void convertSerial(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {

                int rgb = image.getRGB(x, y);
                int gray = calculateHueForPixel(rgb);

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
                    int gray = calculateHueForPixel(rgb);

                    image.setRGB(x, y, gray);
                }
            }
        });
        ImageProcessor.getInstance().getImagePanel().replaceImage(image);
    }

    private int calculateHueForPixel(int rgb) {

        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb & 0xFF);

        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        float hue = hsb[0] + 0.1f;

        return Color.HSBtoRGB(hue, hsb[1], hsb[2]);
    }
}
