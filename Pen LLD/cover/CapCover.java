package cover;

/**
 * CapCover.java
 * --------------
 * Concrete implementation of the {@link Cover} strategy for cap-based pens.
 *
 * A cap cover works by physically removing a protective cap to expose the nib
 * before writing, and placing the cap back on when finished. This is the
 * traditional mechanism found on fountain pens and most marker pens.
 *
 * @see Cover
 * @see ClickCover
 */
public class CapCover implements Cover {

    /**
     * Removes the protective cap from the pen, exposing the nib for writing.
     */
    @Override
    public void open() {
        System.out.println("[Cap] Pulling off the protective cap.");
    }

    /**
     * Places the protective cap back onto the pen to shield the nib.
     */
    @Override
    public void shut() {
        System.out.println("[Cap] Securing the cap back onto the pen.");
    }
}
