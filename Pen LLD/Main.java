import model.Pen;
import factory.PenFactory;

/**
 * Main.java
 * -----------
 * Entry point that demonstrates the Pen system end-to-end.
 *
 * Creates three different pen configurations using the {@link PenFactory}
 * and exercises all four behaviors: {@code start()}, {@code write()},
 * {@code refill()}, and {@code close()}. Also demonstrates the state
 * validation (attempting to write on a closed pen).
 */
public class Main {

    public static void main(String[] args) {

        /*
         * Scenario 1 — A gel pen with a cap cover and cartridge refill.
         * This is common for premium gel pens used in journaling.
         */
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Scenario 1: Gel Pen (Cap + Cartridge)     ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        Pen gelPen = PenFactory.createPen("gel", "cap", "cartridge", "Royal Blue");
        gelPen.start();
        gelPen.write("Design patterns are powerful!");
        gelPen.refill("Midnight Black");
        gelPen.write("Now writing after a refill.");
        gelPen.close();

        System.out.println();

        /*
         * Scenario 2 — A ballpoint pen with click mechanism and pipe refill.
         * The standard office pen. Also tests state validation.
         */
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Scenario 2: Ball Pen (Click + Pipe)       ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        Pen ballPen = PenFactory.createPen("ball", "click", "pipe", "Black");
        ballPen.write("This should FAIL — pen is still closed!");
        ballPen.start();
        ballPen.write("Meeting notes: review the LLD diagram.");
        ballPen.close();

        System.out.println();

        /*
         * Scenario 3 — An ink (fountain) pen with cap and cartridge.
         * Elegant pen for calligraphy and formal writing.
         */
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Scenario 3: Ink Pen (Cap + Cartridge)     ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        Pen inkPen = PenFactory.createPen("ink", "cap", "cartridge", "Forest Green");
        inkPen.start();
        inkPen.write("Elegant calligraphy practice.");
        inkPen.refill("Crimson Red");
        inkPen.write("Switched colors mid-session!");
        inkPen.close();
    }
}
