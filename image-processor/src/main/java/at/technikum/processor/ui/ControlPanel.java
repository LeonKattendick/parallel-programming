package at.technikum.processor.ui;

import at.technikum.processor.listener.ImageLoadingListener;
import at.technikum.processor.listener.ImageModifyListener;
import at.technikum.processor.listener.ParallelSwitchListener;
import at.technikum.processor.util.ImageAction;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ControlPanel extends JPanel {

    private final JButton saveButton;

    private final JButton executeButton;

    private final JComboBox<ImageAction> comboBox;

    private final JButton parallelSwitchButton;

    private final JLabel timeLabel;

    private boolean parallelEnabled = true;

    public ControlPanel() {
        JButton importButton = new ControlJButton("Importieren");
        importButton.addActionListener(new ImageLoadingListener());

        saveButton = new ControlJButton("Speichern");
        saveButton.setEnabled(false);

        comboBox = new JComboBox<>(ImageAction.values());

        executeButton = new ControlJButton("Anwenden");
        executeButton.setEnabled(false);
        executeButton.addActionListener(new ImageModifyListener());

        parallelSwitchButton = new ControlJButton("Parallel");
        parallelSwitchButton.addActionListener(new ParallelSwitchListener());

        timeLabel = new JLabel();
        writeTime(0);

        add(importButton);
        add(saveButton);
        add(comboBox);
        add(parallelSwitchButton);
        add(executeButton);
        add(timeLabel);
    }

    public void enableImageEditing() {
        saveButton.setEnabled(true);
        executeButton.setEnabled(true);
    }

    public void switchParallelState() {
        parallelEnabled = !parallelEnabled;
        parallelSwitchButton.setText(parallelEnabled ? "Parallel" : "Seriell");
    }

    public void writeTime(long time) {
        timeLabel.setText("Zeit: " + time + "ms");
    }
}
