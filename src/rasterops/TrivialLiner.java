package rasterops;

import rasterdata.RasterImage;

public class TrivialLiner<P> implements Liner<P> {


    @Override
    public void drawLine(final RasterImage<Integer> img, int x1, int y1, int x2, int y2,
                         int pixelValue) {

        /**
         * deltaY is row diference
         * deltaX is colum diference
         */
        double deltaY = y2 - y1;
        double deltaX = x2 - x1;

        if (Math.abs(deltaY) >= Math.abs(deltaX)) {
            if ((deltaX == 0) && (deltaY == 0)) {
                img.setPixel(x1, y1, pixelValue);
            } else {
                img.setPixel(x1, y1, pixelValue);

                /**
                 * Swaps staring and ending points
                 * @param pom        helping variable for swaping points
                 */
                if (y1 > y2) {
                    int pom = x2;
                    x2 = x1;
                    x1 = pom;

                    pom = y2;
                    y2 = y1;
                    y1 = pom;
                }
                double k = (double) (deltaX / deltaY);
                double q = x1 - k * y1;
                for (int y = y1; y <= y2; y++) {
                    int x = (int) (k * y + q);
                    img.setPixel(x, y, pixelValue);
                }
            }

        } else {
            /**
             * Swaps staring and ending points
             * @param pom        helping variable for swaping points
             */
            if (x1 > x2) {
                int pom = x2;
                x2 = x1;
                x1 = pom;

                pom = y2;
                y2 = y1;
                y1 = pom;
            }
            img.setPixel(x2, y2, pixelValue);


            double k = (double) (deltaY / deltaX);
            double q = y1 - k * x1;
            for (int x = x1; x <= x2; x++) {
                int y = (int) (k * x + q);
                img.setPixel(x, y, pixelValue);
            }
        }
    }

    @Override
    public void drawDashedLine(final RasterImage<Integer> img, int x1, int y1, int x2, int y2,
                               int pixelValue) {


        double deltaY = y2 - y1;
        double deltaX = x2 - x1;

        if (Math.abs(deltaY) >= Math.abs(deltaX)) {
            if ((deltaX == 0) && (deltaY == 0)) {
                img.setPixel(x1, y1, pixelValue);
            } else {
                img.setPixel(x1, y1, pixelValue);


                /**
                 * Swaps staring and ending points
                 * @param pom        helping variable for swaping points
                 */
                if (y1 > y2) {
                    int pom = x2;
                    x2 = x1;
                    x1 = pom;

                    pom = y2;
                    y2 = y1;
                    y1 = pom;
                }
                double k = (double) (deltaX / deltaY);
                double q = x1 - k * y1;
                for (int y = y1; y <= y2; y++) {

                    /**
                     *  Makes a dash every few pixels
                     */
                    if (y % 40 < 20) {
                        int x = (int) (k * y + q);
                        img.setPixel(x, y, pixelValue);
                    }

                }
            }

        } else {
            /**
             * Swaps staring and ending points
             * @param pom        helping variable for swaping points
             */
            if (x1 > x2) {
                int pom = x2;
                x2 = x1;
                x1 = pom;

                pom = y2;
                y2 = y1;
                y1 = pom;
            }
            img.setPixel(x2, y2, pixelValue);


            double k = (double) (deltaY / deltaX);
            double q = y1 - k * x1;
            for (int x = x1; x <= x2; x++) {
                /**
                 *  Makes a dash every few pixels
                 */
                if (x % 40 < 20) {

                    int y = (int) (k * x + q);
                    img.setPixel(x, y, pixelValue);
                }
            }
        }
    }
}
