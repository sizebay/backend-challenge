package backend.challenge.modules.task.core.repositories;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;

import java.util.List;
import java.util.UUID;

public interface ITaskRepository {

	Task index(UUID taskId) throws NotFoundTaskException;
	List<Task> show();
	void add(Task task);
	Task update(Task task) throws NotFoundTaskException;
	void delete(UUID taskId) throws NotFoundTaskException;

}
