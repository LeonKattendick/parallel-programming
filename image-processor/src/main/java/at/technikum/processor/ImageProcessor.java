package at.technikum.processor;

import at.technikum.processor.ui.ControlPanel;
import at.technikum.processor.ui.ImagePanel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ImageProcessor extends JFrame {

    @Getter
    private static ImageProcessor instance;

    private final ControlPanel controlPanel;

    private final ImagePanel imagePanel;

    public ImageProcessor() {

        setTitle("Image Processor");
        setMinimumSize(new Dimension(700, 500));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel();
        imagePanel = new ImagePanel();

        add(controlPanel);
        add(imagePanel);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        if (instance != null) throw new RuntimeException("Already running.");

        instance = new ImageProcessor();
    }
}
