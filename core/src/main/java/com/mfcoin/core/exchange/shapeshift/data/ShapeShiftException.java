package com.mfcoin.core.exchange.shapeshift.data;

/**
 * @author John L. Jegutanis
 */
public class ShapeShiftException extends Exception {
    public ShapeShiftException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShapeShiftException(Throwable cause) {
        super(cause);
    }

    public ShapeShiftException(String message) {
        super(message);
    }
}
