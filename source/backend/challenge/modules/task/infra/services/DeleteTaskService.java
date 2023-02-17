package backend.challenge.modules.task.infra.services;

import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.IDeleteTaskService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class DeleteTaskService implements IDeleteTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public DeleteTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void execute(UUID taskId) throws NotFoundTaskException {
		taskRepository.delete(taskId);
	}

}
