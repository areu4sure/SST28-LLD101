package writable;

/**
 * GelWritable.java
 * ------------------
 * Concrete implementation of the {@link Writable} strategy for gel-based pens.
 *
 * Gel pens use a thick, water-based gel ink that produces vibrant, smooth lines.
 * They are popular for art, journaling, and any task that benefits from
 * high-visibility, smudge-resistant output.
 *
 * @see Writable
 * @see BallWritable
 * @see InkWritable
 */
public class GelWritable implements Writable {

    /**
     * Deposits gel ink onto the paper, producing smooth and vibrant strokes.
     *
     * @param inkColor the active gel ink color
     * @param text     the content to write
     */
    @Override
    public void write(String inkColor, String text) {
        System.out.println("[Gel Nib] Smoothly writing in " + inkColor + " gel → \"" + text + "\"");
    }
}
