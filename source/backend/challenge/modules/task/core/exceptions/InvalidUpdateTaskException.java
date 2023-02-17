package backend.challenge.modules.task.core.exceptions;

public class InvalidUpdateTaskException extends Exception {
    public InvalidUpdateTaskException() {
        super("Invalid update task");
    }
}
