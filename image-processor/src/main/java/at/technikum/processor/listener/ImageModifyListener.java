package at.technikum.processor.listener;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.ImageAction;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

@Slf4j
public class ImageModifyListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        ImageAction imageAction = (ImageAction) ImageProcessor.getInstance().getControlPanel().getComboBox().getSelectedItem();
        if (imageAction == null) return;

        BufferedImage image = ImageProcessor.getInstance().getImagePanel().getImage();

        log.info("Trying to use {} on image", imageAction);
        imageAction.getStrategy().convert(image);

    }
}
