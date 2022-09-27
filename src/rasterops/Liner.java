package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

/**
 * Represent an algorithms for drawing lines on a {@link rasterdata.RasterImage} using a pixel of the given type
 * @param <P> pixel type
 */
public interface Liner<P> {

    /**
     *
     * @param img
     * @param c1
     * @param r1
     * @param c2
     * @param r2
     * @param pixelValue
     */
    void drawLine(final @NotNull RasterImage<P> img, final int c1,
                  final int r1, final int c2, final int r2, final @NotNull P pixelValue);
}
