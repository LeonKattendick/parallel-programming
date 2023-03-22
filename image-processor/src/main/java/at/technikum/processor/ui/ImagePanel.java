package at.technikum.processor.ui;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    public ImagePanel() {
        JLabel jLabel = new JLabel("Lade ein Bild über den Schalter \"Importieren\".");
        jLabel.setForeground(Color.GRAY);

        add(jLabel);
    }
}
