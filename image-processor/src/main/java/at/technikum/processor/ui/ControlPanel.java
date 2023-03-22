package at.technikum.processor.ui;

import at.technikum.processor.listener.ImageLoadingListener;

import javax.swing.*;

public class ControlPanel extends JPanel {

    public ControlPanel() {
        JButton importButton = new JButton("Importieren");
        importButton.setFocusPainted(false);
        importButton.addActionListener(new ImageLoadingListener());

        JButton saveButton = new JButton("Speichern");
        saveButton.setEnabled(false);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Graustufen"});

        JButton executeButton = new JButton("Anwenden");
        executeButton.setEnabled(false);

        add(importButton);
        add(saveButton);
        add(comboBox);
        add(executeButton);
    }
}
