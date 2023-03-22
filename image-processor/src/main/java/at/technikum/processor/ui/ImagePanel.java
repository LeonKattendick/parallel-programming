package at.technikum.processor.ui;

import at.technikum.processor.ImageProcessor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.AsyncScalr;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ExecutionException;

@Slf4j
public class ImagePanel extends JPanel {

    private final JLabel label;

    private BufferedImage image;

    private int resizedWidth;

    private int resizedHeight;

    public ImagePanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        label = new JLabel("Lade ein Bild über den Schalter \"Importieren\".");
        label.setForeground(Color.GRAY);
        label.setHorizontalAlignment(JLabel.CENTER);

        add(label);
    }

    @SneakyThrows
    public void loadImage(File file) {
        log.info("Trying to load image {}", file);

        image = ImageIO.read(file);

        label.setText(null);
        resizeImage(ImageProcessor.getInstance().getImagePanel().getSize());
    }

    public void resizeImage(Dimension dimension) {
        if (image == null) return;
        if (resizedWidth == dimension.getWidth() && resizedHeight == dimension.getHeight()) return;

        resizedWidth = (int) dimension.getWidth();
        resizedHeight = (int) dimension.getHeight();

        log.info("Resizing image to width {} and height {}", resizedWidth, resizedHeight);

        Image labelImage = Scalr.resize(image, Scalr.Method.QUALITY, resizedWidth, resizedHeight, Scalr.OP_ANTIALIAS);
        label.setIcon(new ImageIcon(labelImage));
    }
}
