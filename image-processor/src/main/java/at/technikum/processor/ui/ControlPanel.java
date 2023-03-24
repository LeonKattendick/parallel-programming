package at.technikum.processor.ui;

import at.technikum.processor.listener.ImageLoadingListener;
import at.technikum.processor.listener.ImageModifyListener;
import at.technikum.processor.util.ImageAction;
import lombok.Getter;

import javax.swing.*;

@Getter
public class ControlPanel extends JPanel {

    JComboBox<ImageAction> comboBox = new JComboBox<>(ImageAction.values());

    public ControlPanel() {
        JButton importButton = new JButton("Importieren");
        importButton.setFocusPainted(false);
        importButton.addActionListener(new ImageLoadingListener());

        JButton saveButton = new JButton("Speichern");
        saveButton.setEnabled(false);

        JButton executeButton = new JButton("Anwenden");
        executeButton.addActionListener(new ImageModifyListener());
        executeButton.setEnabled(false);

        add(importButton);
        add(saveButton);
        add(comboBox);
        add(executeButton);
    }
}
