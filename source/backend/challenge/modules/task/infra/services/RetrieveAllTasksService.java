package backend.challenge.modules.task.infra.services;

import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.IRetrieveAllTasksService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RetrieveAllTasksService implements IRetrieveAllTasksService {

	private final ITaskRepository taskRepository;

	@Inject
	public RetrieveAllTasksService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> execute() {
		return taskRepository.show();
	}

}
