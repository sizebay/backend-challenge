package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;

public interface IRetrieveTaskByIdService {

	Task execute(Long taskId);

}
