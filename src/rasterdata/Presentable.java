package rasterdata;

/**
 * Repesent a data structure capable of presenting itself on a device of the give type
 * @param <D> device type
 */
public interface Presentable<D> {

    D present(D device);
}
