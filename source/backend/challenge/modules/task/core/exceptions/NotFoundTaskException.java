package backend.challenge.modules.task.core.exceptions;

import java.util.UUID;

public class NotFoundTaskException extends Exception {
    public NotFoundTaskException(UUID taskId) {
        super(String.format("Not found task with id %s", taskId.toString()));
    }
}
