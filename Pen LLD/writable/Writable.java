package writable;

/**
 * Writable.java
 * ---------------
 * Strategy interface representing the writing mechanism of a pen.
 *
 * Each pen type deposits ink onto paper in a fundamentally different way:
 * gel pens use a water-based gel, ballpoints roll a metal ball through oil-based
 * ink, and fountain/ink pens flow liquid ink via capillary action. This interface
 * captures that variability so the {@code Pen} class stays decoupled from the
 * specific writing technology.
 *
 * Design Pattern : Strategy Pattern
 * SOLID Principle: Dependency Inversion — the high-level {@code Pen} module
 *                  depends on this abstraction, not on concrete writing classes.
 *
 * @see GelWritable
 * @see BallWritable
 * @see InkWritable
 */
public interface Writable {

    /**
     * Writes the given text using the pen's current ink color.
     *
     * @param inkColor the active color of ink being used (e.g., "Blue", "Red")
     * @param text     the content to write on paper
     */
    void write(String inkColor, String text);
}
