package backend.challenge.modules.task.core.services;

import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;

public interface IUpdateTaskService {

	Task execute(Task task) throws NotFoundTaskException, InvalidUpdateTaskException;

}
