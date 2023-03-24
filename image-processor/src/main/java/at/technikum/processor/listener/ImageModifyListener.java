package at.technikum.processor.listener;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.ImageAction;
import at.technikum.processor.util.strategy.BlurStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ImageModifyListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        ImageAction imageAction = (ImageAction) ImageProcessor.getInstance().getControlPanel().getComboBox().getSelectedItem();
        BufferedImage image = (BufferedImage) ImageProcessor.getInstance().getImagePanel().getImage();

        //Strategy Pattern
        switch (imageAction){
            case BLUR:
                new BlurStrategy().convert(image);
                break;

            case INVERT:
                break;

            case GREYSCALE:
                break;
        }

    }

}
