package observer;

public class ConsoleNotifier implements Observer {

    @Override
    public void update(String message) {
        System.out.println("[NOTIFICATION] " + message);
    }
}
