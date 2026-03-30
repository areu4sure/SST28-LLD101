package writable;

/**
 * BallWritable.java
 * -------------------
 * Concrete implementation of the {@link Writable} strategy for ballpoint pens.
 *
 * Ballpoint pens use a tiny rotating metal ball at the tip to dispense
 * oil-based ink evenly. They are the most widely used writing instruments
 * in offices and everyday use due to their reliability and low cost.
 *
 * @see Writable
 * @see GelWritable
 * @see InkWritable
 */
public class BallWritable implements Writable {

    /**
     * Rolls the ballpoint tip across the paper, depositing oil-based ink.
     *
     * @param inkColor the active ballpoint ink color
     * @param text     the content to write
     */
    @Override
    public void write(String inkColor, String text) {
        System.out.println("[Ball Nib] Rolling " + inkColor + " ink onto paper → \"" + text + "\"");
    }
}
