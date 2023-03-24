package at.technikum.processor.ui;

import at.technikum.processor.listener.ImageLoadingListener;
import at.technikum.processor.listener.ImageModifyListener;
import at.technikum.processor.util.ImageAction;
import lombok.Getter;

import javax.swing.*;

@Getter
public class ControlPanel extends JPanel {

    private final JButton saveButton;

    private final JButton executeButton;

    private final JComboBox<ImageAction> comboBox;

    public ControlPanel() {
        JButton importButton = new ControlJButton("Importieren");
        importButton.addActionListener(new ImageLoadingListener());

        saveButton = new ControlJButton("Speichern");
        saveButton.setEnabled(false);

        comboBox = new JComboBox<>(ImageAction.values());

        executeButton = new ControlJButton("Anwenden");
        executeButton.setEnabled(false);
        executeButton.addActionListener(new ImageModifyListener());

        add(importButton);
        add(saveButton);
        add(comboBox);
        add(executeButton);
    }

    public void enableImageEditing() {
        saveButton.setEnabled(true);
        executeButton.setEnabled(true);
    }
}
