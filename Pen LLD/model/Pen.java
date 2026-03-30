package model;

import cover.Cover;
import refill.Refill;
import writable.Writable;

/**
 * Pen.java
 * ----------
 * The central orchestrator class of the Pen system.
 *
 * A {@code Pen} is composed of three interchangeable strategy components:
 * <ul>
 *   <li>{@link Writable} — determines HOW the pen deposits ink (gel, ball, ink).</li>
 *   <li>{@link Cover}    — determines HOW the pen is opened/closed (cap, click).</li>
 *   <li>{@link Refill}   — determines HOW ink is replenished (cartridge, pipe).</li>
 * </ul>
 *
 * <p><strong>State Management:</strong> The pen tracks whether it is currently
 * {@code open} or {@code closed}. Writing is only permitted when the pen is open.
 * This prevents accidental writes on a closed/capped pen.</p>
 *
 * <p><strong>Design Patterns:</strong></p>
 * <ul>
 *   <li><em>Strategy Pattern</em> — each component is injected via constructor,
 *       allowing any combination at runtime.</li>
 *   <li><em>Composition over Inheritance</em> — the Pen class is not subclassed
 *       for each variant; behavior is composed from strategies.</li>
 * </ul>
 *
 * @see cover.Cover
 * @see writable.Writable
 * @see refill.Refill
 */
public class Pen {

    /** The current ink color loaded in this pen. */
    private String activeColor;

    /** Tracks whether the pen is currently open and ready for writing. */
    private boolean isReady;

    /** Strategy: defines the writing mechanism. */
    private final Writable writingStrategy;

    /** Strategy: defines the cover/opening mechanism. */
    private final Cover coverStrategy;

    /** Strategy: defines the refill/ink replenishment mechanism. */
    private final Refill refillStrategy;

    /**
     * Constructs a fully assembled {@code Pen} with the given strategies.
     *
     * @param activeColor     the initial ink color (e.g., "Blue", "Royal Blue")
     * @param writingStrategy the writing mechanism to use
     * @param coverStrategy   the cover/open-close mechanism to use
     * @param refillStrategy  the refill mechanism to use
     */
    public Pen(String activeColor, Writable writingStrategy, Cover coverStrategy, Refill refillStrategy) {
        this.activeColor = activeColor;
        this.writingStrategy = writingStrategy;
        this.coverStrategy = coverStrategy;
        this.refillStrategy = refillStrategy;
        this.isReady = false;  // pen starts in closed state
    }

    /**
     * Opens the pen by delegating to the {@link Cover#open()} strategy.
     * After this call the pen is in a "ready" state and writing is allowed.
     */
    public void start() {
        coverStrategy.open();
        this.isReady = true;
        System.out.println(">> Pen is now OPEN and ready to write.");
    }

    /**
     * Writes the given content using the injected {@link Writable} strategy.
     * If the pen is not open, an error message is printed instead.
     *
     * @param content the text to write
     */
    public void write(String content) {
        if (!isReady) {
            System.out.println("⚠ Cannot write — the pen is still closed! Call start() first.");
            return;
        }
        writingStrategy.write(activeColor, content);
    }

    /**
     * Refills the pen with a new ink color using the {@link Refill} strategy.
     * Also updates the pen's active color to reflect the change.
     *
     * @param newColor the new ink color to load
     */
    public void refill(String newColor) {
        refillStrategy.refill(newColor);
        this.activeColor = newColor;
        System.out.println(">> Active color updated to: " + newColor);
    }

    /**
     * Closes the pen by delegating to the {@link Cover#shut()} strategy.
     * After this call, writing is no longer permitted until {@link #start()} is called again.
     */
    public void close() {
        coverStrategy.shut();
        this.isReady = false;
        System.out.println(">> Pen is now CLOSED.");
    }
}
