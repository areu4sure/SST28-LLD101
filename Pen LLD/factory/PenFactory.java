package factory;

import cover.CapCover;
import cover.ClickCover;
import cover.Cover;
import model.Pen;
import refill.CartridgeRefill;
import refill.PlasticPipeRefill;
import refill.Refill;
import writable.BallWritable;
import writable.GelWritable;
import writable.InkWritable;
import writable.Writable;

/**
 * PenFactory.java
 * -----------------
 * Factory class that centralizes the construction of {@link Pen} objects.
 *
 * <p>The client provides four simple string identifiers and the factory
 * internally resolves them to the correct concrete strategy implementations,
 * wires everything together, and returns a fully assembled {@code Pen}.</p>
 *
 * <h3>Supported identifiers:</h3>
 * <table>
 *   <tr><th>Parameter</th><th>Accepted Values</th></tr>
 *   <tr><td>nibType</td><td>"gel", "ball", "ink"</td></tr>
 *   <tr><td>coverType</td><td>"cap", "click"</td></tr>
 *   <tr><td>refillType</td><td>"cartridge", "pipe"</td></tr>
 * </table>
 *
 * <p><strong>Design Pattern:</strong> Simple Factory — hides the {@code new}
 * keyword from the client and makes pen creation a single method call.</p>
 *
 * @see Pen
 */
public class PenFactory {

    /**
     * Assembles and returns a new {@link Pen} based on the given identifiers.
     *
     * @param nibType    the type of writing nib ("gel", "ball", or "ink")
     * @param coverType  the type of cover mechanism ("cap" or "click")
     * @param refillType the type of refill mechanism ("cartridge" or "pipe")
     * @param inkColor   the initial ink color for the pen
     * @return a fully configured {@code Pen} instance
     * @throws IllegalArgumentException if any identifier is unrecognized
     */
    public static Pen createPen(String nibType, String coverType, String refillType, String inkColor) {

        // -------- Resolve Writable strategy --------
        Writable nib = resolveNib(nibType);

        // -------- Resolve Cover strategy --------
        Cover cover = resolveCover(coverType);

        // -------- Resolve Refill strategy --------
        Refill refill = resolveRefill(refillType);

        // -------- Assemble and return --------
        return new Pen(inkColor, nib, cover, refill);
    }

    // ===================== Private Helper Methods =====================

    /**
     * Maps a nib-type string to the corresponding {@link Writable} implementation.
     */
    private static Writable resolveNib(String nibType) {
        switch (nibType.toLowerCase()) {
            case "gel":  return new GelWritable();
            case "ball": return new BallWritable();
            case "ink":  return new InkWritable();
            default:
                throw new IllegalArgumentException("Unrecognized nib type: \"" + nibType
                        + "\". Supported values: gel, ball, ink");
        }
    }

    /**
     * Maps a cover-type string to the corresponding {@link Cover} implementation.
     */
    private static Cover resolveCover(String coverType) {
        switch (coverType.toLowerCase()) {
            case "cap":   return new CapCover();
            case "click": return new ClickCover();
            default:
                throw new IllegalArgumentException("Unrecognized cover type: \"" + coverType
                        + "\". Supported values: cap, click");
        }
    }

    /**
     * Maps a refill-type string to the corresponding {@link Refill} implementation.
     */
    private static Refill resolveRefill(String refillType) {
        switch (refillType.toLowerCase()) {
            case "cartridge": return new CartridgeRefill();
            case "pipe":      return new PlasticPipeRefill();
            default:
                throw new IllegalArgumentException("Unrecognized refill type: \"" + refillType
                        + "\". Supported values: cartridge, pipe");
        }
    }
}
