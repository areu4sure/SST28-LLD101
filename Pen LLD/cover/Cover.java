package cover;

/**
 * Cover.java
 * -----------
 * Strategy interface representing the opening and closing mechanism of a pen.
 *
 * Different pens use different mechanisms to protect the nib when not in use.
 * For example, some pens have a removable cap, while others use a retractable
 * click mechanism. This interface abstracts that behavior so the {@code Pen}
 * class can delegate to any implementation without knowing the details.
 *
 * Design Pattern : Strategy Pattern
 * SOLID Principle: Open/Closed Principle — new cover types can be added
 *                  by implementing this interface, without modifying existing code.
 *
 * @see CapCover
 * @see ClickCover
 */
public interface Cover {

    /**
     * Opens the pen so it is ready for writing.
     * Each concrete cover defines its own mechanism (e.g., pull cap, click button).
     */
    void open();

    /**
     * Closes the pen to protect the nib after use.
     * Mirrors the {@link #open()} action in reverse.
     */
    void shut();
}
