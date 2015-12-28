package objects;

/**
 * Created by andremmfaria on 21/09/15.
 */
public class QueueElement {
    private String command;

    public QueueElement(String s) {
        command = s;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
