package at.technikum.processor.listener;

import at.technikum.processor.ImageProcessor;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

@Slf4j
public class ResizeListener implements ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) {
        ImageProcessor.getInstance().getImagePanel().resizeImage(e.getComponent().getSize());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
