package at.technikum.processor.listener;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.ImageFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImageSavingListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new ImageFilter());

        int result = fileChooser.showSaveDialog(fileChooser);
        if (result != JFileChooser.APPROVE_OPTION) return;

        File file = fileChooser.getSelectedFile();
        if (!file.getName().contains(".")) file = new File(file + ".png");

        ImageProcessor.getInstance().getImagePanel().saveImage(file);

    }
}
