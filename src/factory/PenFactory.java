package factory;

import enums.InkColor;
import pens.ClickablePen;
import pens.NonClickablePen;

public class PenFactory {

    public static ClickablePen createClickablePen(String brand, InkColor color) {
        return new ClickablePen(brand, color);
    }

    public static NonClickablePen createNonClickablePen(String brand, InkColor color) {
        return new NonClickablePen(brand, color);
    }
}
