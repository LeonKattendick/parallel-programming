package at.technikum.processor.listener;

import at.technikum.processor.ImageProcessor;
import at.technikum.processor.util.ImageAction;
import at.technikum.processor.util.Stopwatch;
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
        boolean parallelEnabled = ImageProcessor.getInstance().getControlPanel().isParallelEnabled();

        log.info("Trying to use {} on image (parallel = {})", imageAction, parallelEnabled);
        Stopwatch stopwatch = new Stopwatch();

        if (parallelEnabled) {
            imageAction.getStrategy().convertParallel(image);
        } else {
            imageAction.getStrategy().convertSerial(image);
        }

        long timeTaken = stopwatch.stop();
        log.info("Conversion {} (parallel = {}) took {}ms", imageAction, parallelEnabled, timeTaken);
        ImageProcessor.getInstance().getControlPanel().writeTime(timeTaken);

    }
}
