package at.technikum.processor.util;

import at.technikum.processor.util.strategy.ImageStrategy;
import at.technikum.processor.util.strategy.impl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageAction {

    GREYSCALE("Graustufen", new GrayscaleStrategy()),
    INVERT("Invertieren", new InvertStrategy()),
    HUE_UP("Hue", new HueStrategy()),
    SATURATION_UP("Saturation (hoch)", new SaturationUpStrategy()),
    SATURATION_DOWN("Saturation (runter)", new SaturationDownStrategy()),
    BRIGHTNESS_UP("Helligkeit (hoch)", new BrightnessUpStrategy()),
    BRIGHTNESS_DOWN("Helligkeit (runter)", new BrightnessDownStrategy());

    private final String name;

    private final ImageStrategy strategy;

    @Override
    public String toString() {
        return this.name;
    }
}
