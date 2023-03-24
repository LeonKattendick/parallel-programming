package at.technikum.processor.listener;

import at.technikum.processor.ImageProcessor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParallelSwitchListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageProcessor.getInstance().getControlPanel().switchParallelState();
    }
}
