package rasterdata;

import org.jetbrains.annotations.NotNull;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.Optional;

/**
 * Represent a 2D raster with pixels of the given type
 * @param <P> pixel type
 */
public interface RasterImage<P> {

    /**
     * Returns the number of columns
     * @return
     */
    int getWidth();

    /**
     * Returns the number or rows
     * @return
     */
    int getHeight();

    /**
     * Return the pixel value at the specified address
     * @param c column address
     * @param r row address
     * @return
     */
    Optional<P> getPixel(int c, int r);

    /**
     * Changes the pixel value to the new provided pixel value at the specified address
     * @param c column address
     * @param r row address
     * @param newValue new pixel value
     */
    void setPixel(int c, int r, P newValue);

    /**
     * Sets values of all pixels to the provided pixel value
     * @param newValue new pixel value
     */
    void clear(final @NotNull P newValue);
}
