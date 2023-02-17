package backend.challenge.modules.task.core.services;


import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;

import java.util.UUID;

public interface IDeleteTaskService {

	void execute(UUID taskId) throws NotFoundTaskException;

}
