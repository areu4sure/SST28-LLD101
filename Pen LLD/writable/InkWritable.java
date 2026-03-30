package writable;

/**
 * InkWritable.java
 * ------------------
 * Concrete implementation of the {@link Writable} strategy for fountain / ink pens.
 *
 * Ink pens (fountain pens) rely on gravity and capillary action to draw
 * liquid ink from an internal reservoir through a feed and onto a metal nib.
 * They produce elegant, variable-width lines prized in calligraphy and
 * formal correspondence.
 *
 * @see Writable
 * @see GelWritable
 * @see BallWritable
 */
public class InkWritable implements Writable {

    /**
     * Flows liquid ink through the nib via capillary action onto the paper.
     *
     * @param inkColor the active fountain ink color
     * @param text     the content to write
     */
    @Override
    public void write(String inkColor, String text) {
        System.out.println("[Ink Nib] Flowing " + inkColor + " ink elegantly → \"" + text + "\"");
    }
}
