package at.technikum.processor.util;

import at.technikum.processor.util.strategy.impl.GrayscaleStrategy;
import at.technikum.processor.util.strategy.ImageStrategy;
import at.technikum.processor.util.strategy.impl.InvertStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageAction {

    GREYSCALE("Graustufen", new GrayscaleStrategy()),
    INVERT("Invertieren", new InvertStrategy());

    private final String name;

    private final ImageStrategy strategy;

    @Override
    public String toString() {
        return this.name;
    }
}
