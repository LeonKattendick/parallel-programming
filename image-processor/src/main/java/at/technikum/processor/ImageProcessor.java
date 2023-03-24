package at.technikum.processor;

import at.technikum.processor.listener.ResizeListener;
import at.technikum.processor.ui.ControlPanel;
import at.technikum.processor.ui.ImagePanel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

@Getter
public class ImageProcessor extends JFrame {

    @Getter
    private static ImageProcessor instance;

    private final ControlPanel controlPanel;

    private final ImagePanel imagePanel;

    public ImageProcessor() {

        setTitle("Image Processor");
        setMinimumSize(new Dimension(1280, 720));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        addComponentListener(new ResizeListener());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        URL logoUrl = ImageProcessor.class.getResource("/images/icon.png");
        if (logoUrl != null) setIconImage(new ImageIcon(logoUrl).getImage());

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
