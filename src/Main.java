import enums.InkColor;
import factory.PenFactory;
import pens.ClickablePen;
import pens.NonClickablePen;

public class Main {

    public static void main(String[] args) {

        // clickable pen - opens and closes with a click
        ClickablePen parker = PenFactory.createClickablePen("Parker", InkColor.BLUE);
        System.out.println(parker);

        parker.write("hi");         // won't work, pen is closed
        parker.click();             // open
        parker.write("hello world");
        parker.write("SOLID is cool");
        parker.click();             // close
        parker.write("hi again");   // won't work, pen is closed

        System.out.println();

        // drain the ink
        parker.click();
        for (int i = 1; i <= 10; i++) {
            parker.write("line " + i);
        }
        parker.write("after empty"); // won't work, ink is empty

        // refill and write again
        parker.refill();
        parker.write("back to writing!");

        System.out.println();

        // non-clickable pen - opens and closes with a cap
        NonClickablePen classmate = PenFactory.createNonClickablePen("Classmate", InkColor.BLACK);
        System.out.println(classmate);

        classmate.write("won't work, cap is on");
        classmate.open();
        classmate.write("LLD assignment done!");
        classmate.close();
        classmate.write("cap is back on, won't work");

        System.out.println();

        // a red pen just for fun
        ClickablePen pilot = PenFactory.createClickablePen("Pilot", InkColor.RED);
        System.out.println(pilot);
        pilot.open();
        pilot.write("marking in red!");
        pilot.close();
    }
}
