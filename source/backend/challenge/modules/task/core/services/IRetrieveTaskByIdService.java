package backend.challenge.modules.task.core.services;

import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;

import java.util.UUID;

public interface IRetrieveTaskByIdService {

	Task execute(UUID taskId) throws NotFoundTaskException;

}
