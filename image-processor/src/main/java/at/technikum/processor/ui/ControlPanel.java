package at.technikum.processor.ui;

import javax.swing.*;

public class ControlPanel extends JPanel {

    public ControlPanel() {
        JButton importButton = new JButton("Importieren");
        importButton.setFocusPainted(false);

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
