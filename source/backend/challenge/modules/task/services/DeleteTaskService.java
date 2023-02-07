package backend.challenge.modules.task.services;

import backend.challenge.modules.task.repositories.ITaskRepository;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteTaskService implements IDeleteTaskService
{

	private final ITaskRepository taskRepository;

	@Inject
	public DeleteTaskService(final ITaskRepository taskRepository)
	{
		this.taskRepository = taskRepository;
	}

	@Override
	public void execute(Long taskId)
	{
		this.taskRepository.delete(taskId);
	}

}
