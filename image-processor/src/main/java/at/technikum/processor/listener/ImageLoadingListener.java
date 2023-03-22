package at.technikum.processor.listener;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.ImageFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImageLoadingListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new ImageFilter());

        int result = fileChooser.showOpenDialog(fileChooser);
        if (result != JFileChooser.APPROVE_OPTION) return;

        File file = fileChooser.getSelectedFile();
        ImageProcessor.getInstance().getImagePanel().loadImage(file);

    }
}
