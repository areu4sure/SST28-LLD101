package refill;

/**
 * Refill.java
 * -------------
 * Strategy interface representing the ink replenishment mechanism of a pen.
 *
 * Different pens replenish ink in different ways — some use replaceable
 * cartridges that are swapped entirely, while others have a plastic pipe
 * (tube) that can be re-injected with fresh ink. This interface abstracts
 * the refill behavior so the {@code Pen} class remains independent of
 * the specific mechanism.
 *
 * Design Pattern : Strategy Pattern
 * SOLID Principle: Interface Segregation — only pens that support refilling
 *                  compose this interface. A Pencil, for example, would
 *                  simply not use a {@code Refill} at all.
 *
 * @see CartridgeRefill
 * @see PlasticPipeRefill
 */
public interface Refill {

    /**
     * Replenishes the pen's ink supply with the specified color.
     *
     * @param newColor the new ink color to load into the pen
     */
    void refill(String newColor);
}
