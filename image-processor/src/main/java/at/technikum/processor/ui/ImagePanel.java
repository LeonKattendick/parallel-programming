package at.technikum.processor.ui;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.FileUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Slf4j
@Getter
public class ImagePanel extends JPanel {

    private final JLabel label;

    @Setter
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

        replaceImage(ImageIO.read(file));
        label.setText(null);
        ImageProcessor.getInstance().getControlPanel().enableImageEditing();
    }

    @SneakyThrows
    public void saveImage(File file) {
        log.info("Trying to save image {}", file);
        ImageIO.write(image, FileUtil.getImageTypeFromFile(file), file);
    }

    public void replaceImage(BufferedImage image) {
        this.image = image;
        resizeImage(ImageProcessor.getInstance().getImagePanel().getSize());
    }

    public void resizeImageOnSizeChange(Dimension dimension) {
        if (resizedWidth == dimension.getWidth() && resizedHeight == dimension.getHeight()) resizeImage(dimension);
    }

    private void resizeImage(Dimension dimension) {
        if (image == null) return;

        resizedWidth = (int) dimension.getWidth();
        resizedHeight = (int) dimension.getHeight();

        log.info("Resizing image to width {} and height {}", resizedWidth, resizedHeight);

        Image labelImage = Scalr.resize(image, Scalr.Method.QUALITY, resizedWidth, resizedHeight, Scalr.OP_ANTIALIAS);
        label.setIcon(new ImageIcon(labelImage));
    }
}
