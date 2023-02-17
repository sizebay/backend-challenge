package backend.challenge.modules.task.core.services;

import backend.challenge.modules.task.core.models.Task;

import java.util.List;

public interface IRetrieveAllTasksService {

	List<Task> execute();

}
