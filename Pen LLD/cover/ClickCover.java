package cover;

/**
 * ClickCover.java
 * -----------------
 * Concrete implementation of the {@link Cover} strategy for click-action pens.
 *
 * A click cover uses a spring-loaded button mechanism to push the nib outward
 * for writing, and retract it back inside the barrel when done. Commonly
 * seen in ballpoint retractable pens.
 *
 * @see Cover
 * @see CapCover
 */
public class ClickCover implements Cover {

    /**
     * Presses the click mechanism to push the nib outward, readying the pen.
     */
    @Override
    public void open() {
        System.out.println("[Click] Pressing button to push the nib out.");
    }

    /**
     * Presses the click mechanism again to retract the nib back inside.
     */
    @Override
    public void shut() {
        System.out.println("[Click] Pressing button to pull the nib back in.");
    }
}
