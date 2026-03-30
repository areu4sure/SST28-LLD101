package parkinglot.model;

/**
 * Represents an entry point into the parking lot.
 */
public class EntryGate {
    private final String gateId;

    public EntryGate(String gateId) {
        this.gateId = gateId;
    }

    public String getGateId() {
        return gateId;
    }

    @Override
    public String toString() {
        return "EntryGate{" +
                "gateId='" + gateId + '\'' +
                '}';
    }
}
