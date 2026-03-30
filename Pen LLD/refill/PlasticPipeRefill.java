package refill;

/**
 * PlasticPipeRefill.java
 * ------------------------
 * Concrete implementation of the {@link Refill} strategy using a refillable plastic pipe.
 *
 * Plastic pipe (tube) refills allow ink to be injected directly into the
 * transparent tube inside the pen. Rather than discarding the entire cartridge,
 * the user simply draws or squeezes new ink into the existing pipe. This is
 * more economical and commonly seen in fountain pens and some budget ballpoints.
 *
 * @see Refill
 * @see CartridgeRefill
 */
public class PlasticPipeRefill implements Refill {

    /**
     * Draws fresh ink of the given color into the reusable plastic pipe (tube).
     *
     * @param newColor the color of ink to inject into the pipe
     */
    @Override
    public void refill(String newColor) {
        System.out.println("[Pipe] Drawing fresh " + newColor + " ink into the plastic tube.");
    }
}
