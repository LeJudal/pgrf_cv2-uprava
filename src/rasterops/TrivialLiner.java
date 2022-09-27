package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public class TrivialLiner<P> implements Liner<P>{


    @Override
    public void drawLine(final @NotNull RasterImage<P> img, final int c1, final int r1,
                         final int c2, final int r2, final @NotNull P pixelValue) {
        final double k = (r2 - r1) / (double) (c2 - c1);
        final double q = r1 - k * c1;
        for (int c = c1; c <= c2; c++){
            int r = (int) Math.round(k * c + q);
            img.setPixel(c, r, pixelValue);
        }

        if (Math.abs(r2 - r1) < Math.abs(c2 - c1)) { //iterate over c
            if (c1 > c2) { // swap start and end

            }
            // iterating over c and calculating r
        } else { // iterate over r
            if (r1 > r2) { // swap start and end

            }
            // iterating over r and calculating r
        }
    }
}
