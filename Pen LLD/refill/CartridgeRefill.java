package refill;

/**
 * CartridgeRefill.java
 * ----------------------
 * Concrete implementation of the {@link Refill} strategy using replaceable cartridges.
 *
 * Cartridge refills work by ejecting the spent ink cartridge from the pen barrel
 * and inserting a brand-new, factory-sealed cartridge of the desired color.
 * This is the most common refill mechanism in modern gel and ballpoint pens.
 *
 * @see Refill
 * @see PlasticPipeRefill
 */
public class CartridgeRefill implements Refill {

    /**
     * Ejects the old cartridge and inserts a fresh one with the requested color.
     *
     * @param newColor the color of the replacement cartridge
     */
    @Override
    public void refill(String newColor) {
        System.out.println("[Cartridge] Ejecting spent cartridge and loading a fresh " + newColor + " cartridge.");
    }
}
